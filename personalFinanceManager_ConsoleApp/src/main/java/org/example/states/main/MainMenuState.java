package org.example.states.main;

import org.example.context.ConsoleContext;
import org.example.states.AppState;
import org.example.states.auth.InitialState;
import org.example.states.budget.BudgetMenuState;
import org.example.states.category.CategoryMenuState;
import org.example.states.expense.ExpenseMenuState;
import org.example.states.income.IncomeMenuState;
import org.example.states.summary.SummaryState;

/**
 * The main navigation hub after a user is authenticated.
 * Allows navigation to all feature areas or logout.
 */
public class MainMenuState implements AppState {

    @Override
    public AppState execute(ConsoleContext context) {
        System.out.println("\n========================================");
        System.out.println("           Main Menu");
        System.out.println("========================================");
        System.out.println("  1. Categories");
        System.out.println("  2. Expenses");
        System.out.println("  3. Income");
        System.out.println("  4. Budgets");
        System.out.println("  5. Summary Report");
        System.out.println("  0. Logout");
        System.out.println("----------------------------------------");

        final int choice = context.readIntChoice("Enter choice: ");

        return switch (choice) {
            case 1 -> new CategoryMenuState();
            case 2 -> new ExpenseMenuState();
            case 3 -> new IncomeMenuState();
            case 4 -> new BudgetMenuState();
            case 5 -> new SummaryState();
            case 0 -> {
                context.clearToken();
                System.out.println("[✓] Logged out successfully.");
                yield new InitialState();
            }
            default -> {
                System.out.println("[!] Invalid choice. Please try again.");
                yield this;
            }
        };
    }
}
