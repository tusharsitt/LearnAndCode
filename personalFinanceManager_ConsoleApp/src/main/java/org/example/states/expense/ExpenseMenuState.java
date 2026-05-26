package org.example.states.expense;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.context.ConsoleContext;
import org.example.dtos.ExpenseRequestDto;
import org.example.dtos.ExpenseResponseDto;
import org.example.states.AppState;
import org.example.states.main.MainMenuState;
import org.example.util.ApiHelper;

import java.time.LocalDate;
import java.util.List;

/**
 * Handles all Expense CRUD operations via the console menu.
 */
public class ExpenseMenuState implements AppState {

    private static final String EXPENSES_PATH = "/expenses";

    @Override
    public AppState execute(ConsoleContext context) {
        System.out.println("\n--- Expense Menu ---");
        System.out.println("  1. List All Expenses");
        System.out.println("  2. Add Expense");
        System.out.println("  3. Update Expense");
        System.out.println("  4. Delete Expense");
        System.out.println("  0. Back to Main Menu");
        System.out.println("--------------------");

        final int choice = context.readIntChoice("Enter choice: ");

        switch (choice) {
            case 1 -> listExpenses(context);
            case 2 -> addExpense(context);
            case 3 -> updateExpense(context);
            case 4 -> deleteExpense(context);
            case 0 -> { return new MainMenuState(); }
            default -> System.out.println("[!] Invalid choice.");
        }

        return this;
    }

    private void listExpenses(ConsoleContext context) {
        try {
            final List<ExpenseResponseDto> expenses = ApiHelper.get(
                    context, EXPENSES_PATH, new TypeReference<>() {}
            );
            if (expenses.isEmpty()) {
                System.out.println("[i] No expenses found.");
                return;
            }
            System.out.println("\n  ID  | Amount    | Category         | Date");
            System.out.println("  ----|-----------|------------------|------------");
            for (ExpenseResponseDto e : expenses) {
                System.out.printf("  %-4d| %-9.2f | %-16s | %s%n",
                        e.getId(), e.getAmount(),
                        e.getCategory().getCategoryName(), e.getDate());
            }
        } catch (Exception e) {
            System.out.println("[!] Failed to fetch expenses: " + e.getMessage());
        }
    }

    private void addExpense(ConsoleContext context) {
        try {
            final double amount = Double.parseDouble(context.readString("Amount: "));
            final long categoryId = Long.parseLong(context.readString("Category ID: "));
            final LocalDate date = LocalDate.parse(context.readString("Date (YYYY-MM-DD): "));

            final ExpenseRequestDto request = new ExpenseRequestDto();
            request.setAmount(amount);
            request.setCategoryId(categoryId);
            request.setDate(date);

            final ExpenseResponseDto created = ApiHelper.post(
                    context, EXPENSES_PATH, request, new TypeReference<>() {}
            );
            System.out.println("[✓] Expense added: [" + created.getId() + "] $" + created.getAmount());
        } catch (Exception e) {
            System.out.println("[!] Failed to add expense: " + e.getMessage());
        }
    }

    private void updateExpense(ConsoleContext context) {
        try {
            final long id = Long.parseLong(context.readString("Expense ID to update: "));
            final double amount = Double.parseDouble(context.readString("New Amount: "));
            final long categoryId = Long.parseLong(context.readString("New Category ID: "));
            final LocalDate date = LocalDate.parse(context.readString("New Date (YYYY-MM-DD): "));

            final ExpenseRequestDto request = new ExpenseRequestDto();
            request.setAmount(amount);
            request.setCategoryId(categoryId);
            request.setDate(date);

            final ExpenseResponseDto updated = ApiHelper.put(
                    context, EXPENSES_PATH + "/" + id, request, new TypeReference<>() {}
            );
            System.out.println("[✓] Expense updated: [" + updated.getId() + "] $" + updated.getAmount());
        } catch (Exception e) {
            System.out.println("[!] Failed to update expense: " + e.getMessage());
        }
    }

    private void deleteExpense(ConsoleContext context) {
        try {
            final long id = Long.parseLong(context.readString("Expense ID to delete: "));
            final int statusCode = ApiHelper.delete(context, EXPENSES_PATH + "/" + id);
            if (statusCode == 204 || statusCode == 200) {
                System.out.println("[✓] Expense deleted successfully.");
            } else {
                System.out.println("[!] Delete returned status: " + statusCode);
            }
        } catch (Exception e) {
            System.out.println("[!] Failed to delete expense: " + e.getMessage());
        }
    }
}
