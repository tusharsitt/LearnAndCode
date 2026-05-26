package org.example.states.budget;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.context.ConsoleContext;
import org.example.dtos.BudgetRequestDto;
import org.example.dtos.BudgetResponseDto;
import org.example.states.AppState;
import org.example.states.main.MainMenuState;
import org.example.util.ApiHelper;

import java.time.LocalDate;
import java.util.List;

/**
 * Handles all Budget CRUD operations via the console menu.
 */
public class BudgetMenuState implements AppState {

    private static final String BUDGETS_PATH = "/budgets";

    @Override
    public AppState execute(ConsoleContext context) {
        System.out.println("\n--- Budget Menu ---");
        System.out.println("  1. List All Budgets");
        System.out.println("  2. Add Budget");
        System.out.println("  3. Update Budget");
        System.out.println("  4. Delete Budget");
        System.out.println("  0. Back to Main Menu");
        System.out.println("-------------------");

        final int choice = context.readIntChoice("Enter choice: ");

        switch (choice) {
            case 1 -> listBudgets(context);
            case 2 -> addBudget(context);
            case 3 -> updateBudget(context);
            case 4 -> deleteBudget(context);
            case 0 -> { return new MainMenuState(); }
            default -> System.out.println("[!] Invalid choice.");
        }

        return this;
    }

    private void listBudgets(ConsoleContext context) {
        try {
            final List<BudgetResponseDto> budgets = ApiHelper.get(
                    context, BUDGETS_PATH, new TypeReference<>() {}
            );
            if (budgets.isEmpty()) {
                System.out.println("[i] No budgets found.");
                return;
            }
            System.out.println("\n  ID  | Limit     | Category         | Start       | End");
            System.out.println("  ----|-----------|------------------|-------------|------------");
            for (BudgetResponseDto b : budgets) {
                System.out.printf("  %-4d| %-9.2f | %-16s | %-11s | %s%n",
                        b.getId(), b.getLimit(),
                        b.getCategory().getCategoryName(),
                        b.getStartDate(), b.getEndDate());
            }
        } catch (Exception e) {
            System.out.println("[!] Failed to fetch budgets: " + e.getMessage());
        }
    }

    private void addBudget(ConsoleContext context) {
        try {
            final double limit = Double.parseDouble(context.readString("Budget Limit: "));
            final long categoryId = Long.parseLong(context.readString("Category ID: "));
            final LocalDate startDate = LocalDate.parse(context.readString("Start Date (YYYY-MM-DD): "));
            final LocalDate endDate = LocalDate.parse(context.readString("End Date (YYYY-MM-DD): "));

            final BudgetRequestDto request = new BudgetRequestDto();
            request.setLimit(limit);
            request.setCategoryId(categoryId);
            request.setStartDate(startDate);
            request.setEndDate(endDate);

            final BudgetResponseDto created = ApiHelper.post(
                    context, BUDGETS_PATH, request, new TypeReference<>() {}
            );
            System.out.println("[✓] Budget created: [" + created.getId() + "] Limit: $" + created.getLimit());
        } catch (Exception e) {
            System.out.println("[!] Failed to create budget: " + e.getMessage());
        }
    }

    private void updateBudget(ConsoleContext context) {
        try {
            final long id = Long.parseLong(context.readString("Budget ID to update: "));
            final double limit = Double.parseDouble(context.readString("New Limit: "));
            final long categoryId = Long.parseLong(context.readString("New Category ID: "));
            final LocalDate startDate = LocalDate.parse(context.readString("New Start Date (YYYY-MM-DD): "));
            final LocalDate endDate = LocalDate.parse(context.readString("New End Date (YYYY-MM-DD): "));

            final BudgetRequestDto request = new BudgetRequestDto();
            request.setLimit(limit);
            request.setCategoryId(categoryId);
            request.setStartDate(startDate);
            request.setEndDate(endDate);

            final BudgetResponseDto updated = ApiHelper.put(
                    context, BUDGETS_PATH + "/" + id, request, new TypeReference<>() {}
            );
            System.out.println("[✓] Budget updated: [" + updated.getId() + "] Limit: $" + updated.getLimit());
        } catch (Exception e) {
            System.out.println("[!] Failed to update budget: " + e.getMessage());
        }
    }

    private void deleteBudget(ConsoleContext context) {
        try {
            final long id = Long.parseLong(context.readString("Budget ID to delete: "));
            final int statusCode = ApiHelper.delete(context, BUDGETS_PATH + "/" + id);
            if (statusCode == 204 || statusCode == 200) {
                System.out.println("[✓] Budget deleted successfully.");
            } else {
                System.out.println("[!] Delete returned status: " + statusCode);
            }
        } catch (Exception e) {
            System.out.println("[!] Failed to delete budget: " + e.getMessage());
        }
    }
}
