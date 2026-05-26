package org.example.states.income;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.context.ConsoleContext;
import org.example.dtos.IncomeRequestDto;
import org.example.dtos.IncomeResponseDto;
import org.example.states.AppState;
import org.example.states.main.MainMenuState;
import org.example.util.ApiHelper;

import java.time.LocalDate;
import java.util.List;

/**
 * Handles all Income CRUD operations via the console menu.
 */
public class IncomeMenuState implements AppState {

    private static final String INCOME_PATH = "/income";

    @Override
    public AppState execute(ConsoleContext context) {
        System.out.println("\n--- Income Menu ---");
        System.out.println("  1. List All Income");
        System.out.println("  2. Add Income");
        System.out.println("  3. Update Income");
        System.out.println("  4. Delete Income");
        System.out.println("  0. Back to Main Menu");
        System.out.println("-------------------");

        final int choice = context.readIntChoice("Enter choice: ");

        switch (choice) {
            case 1 -> listIncome(context);
            case 2 -> addIncome(context);
            case 3 -> updateIncome(context);
            case 4 -> deleteIncome(context);
            case 0 -> { return new MainMenuState(); }
            default -> System.out.println("[!] Invalid choice.");
        }

        return this;
    }

    private void listIncome(ConsoleContext context) {
        try {
            final List<IncomeResponseDto> incomes = ApiHelper.get(
                    context, INCOME_PATH, new TypeReference<>() {}
            );
            if (incomes.isEmpty()) {
                System.out.println("[i] No income records found.");
                return;
            }
            System.out.println("\n  ID  | Amount    | Category         | Date");
            System.out.println("  ----|-----------|------------------|------------");
            for (IncomeResponseDto i : incomes) {
                System.out.printf("  %-4d| %-9.2f | %-16s | %s%n",
                        i.getId(), i.getIncomeAmount(),
                        i.getCategory().getCategoryName(), i.getDateOfIncome());
            }
        } catch (Exception e) {
            System.out.println("[!] Failed to fetch income: " + e.getMessage());
        }
    }

    private void addIncome(ConsoleContext context) {
        try {
            final double amount = Double.parseDouble(context.readString("Amount: "));
            final long categoryId = Long.parseLong(context.readString("Category ID: "));
            final LocalDate date = LocalDate.parse(context.readString("Date (YYYY-MM-DD): "));

            final IncomeRequestDto request = new IncomeRequestDto();
            request.setIncomeAmount(amount);
            request.setCategoryId(categoryId);
            request.setDateOfIncome(date);

            final IncomeResponseDto created = ApiHelper.post(
                    context, INCOME_PATH, request, new TypeReference<>() {}
            );
            System.out.println("[✓] Income added: [" + created.getId() + "] $" + created.getIncomeAmount());
        } catch (Exception e) {
            System.out.println("[!] Failed to add income: " + e.getMessage());
        }
    }

    private void updateIncome(ConsoleContext context) {
        try {
            final long id = Long.parseLong(context.readString("Income ID to update: "));
            final double amount = Double.parseDouble(context.readString("New Amount: "));
            final long categoryId = Long.parseLong(context.readString("New Category ID: "));
            final LocalDate date = LocalDate.parse(context.readString("New Date (YYYY-MM-DD): "));

            final IncomeRequestDto request = new IncomeRequestDto();
            request.setIncomeAmount(amount);
            request.setCategoryId(categoryId);
            request.setDateOfIncome(date);

            final IncomeResponseDto updated = ApiHelper.put(
                    context, INCOME_PATH + "/" + id, request, new TypeReference<>() {}
            );
            System.out.println("[✓] Income updated: [" + updated.getId() + "] $" + updated.getIncomeAmount());
        } catch (Exception e) {
            System.out.println("[!] Failed to update income: " + e.getMessage());
        }
    }

    private void deleteIncome(ConsoleContext context) {
        try {
            final long id = Long.parseLong(context.readString("Income ID to delete: "));
            final int statusCode = ApiHelper.delete(context, INCOME_PATH + "/" + id);
            if (statusCode == 204 || statusCode == 200) {
                System.out.println("[✓] Income record deleted successfully.");
            } else {
                System.out.println("[!] Delete returned status: " + statusCode);
            }
        } catch (Exception e) {
            System.out.println("[!] Failed to delete income: " + e.getMessage());
        }
    }
}
