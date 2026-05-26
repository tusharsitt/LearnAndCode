package org.example.states.summary;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.context.ConsoleContext;
import org.example.dtos.SummaryResponseDto;
import org.example.states.AppState;
import org.example.states.main.MainMenuState;
import org.example.util.ApiHelper;

import java.util.Map;

/**
 * Displays the financial summary report fetched from the API.
 */
public class SummaryState implements AppState {

    private static final String SUMMARY_PATH = "/summary";

    @Override
    public AppState execute(ConsoleContext context) {
        System.out.println("\n--- Financial Summary Report ---");

        try {
            final SummaryResponseDto summary = ApiHelper.get(
                    context, SUMMARY_PATH, new TypeReference<>() {}
            );

            System.out.println("\n  Total Income  : $" + String.format("%.2f", summary.getTotalIncome()));
            System.out.println("  Total Expense : $" + String.format("%.2f", summary.getTotalExpense()));
            System.out.println("  Balance       : $" + String.format("%.2f", summary.getBalance()));

            if (summary.getCategoryWiseSpending() != null && !summary.getCategoryWiseSpending().isEmpty()) {
                System.out.println("\n  Category-wise Spending:");
                System.out.println("  ----------------------------------");
                for (Map.Entry<String, Double> entry : summary.getCategoryWiseSpending().entrySet()) {
                    System.out.printf("  %-20s: $%.2f%n", entry.getKey(), entry.getValue());
                }
            }

        } catch (Exception e) {
            System.out.println("[!] Failed to fetch summary: " + e.getMessage());
        }

        System.out.println("\nPress Enter to go back...");
        context.readString("");
        return new MainMenuState();
    }
}
