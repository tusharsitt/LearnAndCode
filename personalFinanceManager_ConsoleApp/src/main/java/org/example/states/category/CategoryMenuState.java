package org.example.states.category;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.context.ConsoleContext;
import org.example.dtos.CategoryRequestDto;
import org.example.dtos.CategoryResponseDto;
import org.example.states.AppState;
import org.example.states.main.MainMenuState;
import org.example.util.ApiHelper;

import java.util.List;

/**
 * Handles all Category CRUD operations via the console menu.
 */
public class CategoryMenuState implements AppState {

    private static final String CATEGORIES_PATH = "/categories";

    @Override
    public AppState execute(ConsoleContext context) {
        System.out.println("\n--- Category Menu ---");
        System.out.println("  1. List All Categories");
        System.out.println("  2. Add Category");
        System.out.println("  3. Update Category");
        System.out.println("  4. Delete Category");
        System.out.println("  0. Back to Main Menu");
        System.out.println("---------------------");

        final int choice = context.readIntChoice("Enter choice: ");

        switch (choice) {
            case 1 -> listCategories(context);
            case 2 -> addCategory(context);
            case 3 -> updateCategory(context);
            case 4 -> deleteCategory(context);
            case 0 -> { return new MainMenuState(); }
            default -> System.out.println("[!] Invalid choice.");
        }

        return this;
    }

    private void listCategories(ConsoleContext context) {
        try {
            final List<CategoryResponseDto> categories = ApiHelper.get(
                    context, CATEGORIES_PATH, new TypeReference<>() {}
            );
            if (categories.isEmpty()) {
                System.out.println("[i] No categories found.");
                return;
            }
            System.out.println("\n  ID  | Name");
            System.out.println("  ----|---------------------------");
            for (CategoryResponseDto cat : categories) {
                System.out.printf("  %-4d| %s%n", cat.getId(), cat.getCategoryName());
            }
        } catch (Exception e) {
            System.out.println("[!] Failed to fetch categories: " + e.getMessage());
        }
    }

    private void addCategory(ConsoleContext context) {
        final String name = context.readString("Category Name: ");

        final CategoryRequestDto request = new CategoryRequestDto();
        request.setCategoryName(name);

        try {
            final CategoryResponseDto created = ApiHelper.post(
                    context, CATEGORIES_PATH, request, new TypeReference<>() {}
            );
            System.out.println("[✓] Category created: [" + created.getId() + "] " + created.getCategoryName());
        } catch (Exception e) {
            System.out.println("[!] Failed to create category: " + e.getMessage());
        }
    }

    private void updateCategory(ConsoleContext context) {
        final long id = Long.parseLong(context.readString("Category ID to update: "));
        final String newName = context.readString("New Name: ");

        final CategoryRequestDto request = new CategoryRequestDto();
        request.setCategoryName(newName);

        try {
            final CategoryResponseDto updated = ApiHelper.put(
                    context, CATEGORIES_PATH + "/" + id, request, new TypeReference<>() {}
            );
            System.out.println("[✓] Category updated: [" + updated.getId() + "] " + updated.getCategoryName());
        } catch (Exception e) {
            System.out.println("[!] Failed to update category: " + e.getMessage());
        }
    }

    private void deleteCategory(ConsoleContext context) {
        final long id = Long.parseLong(context.readString("Category ID to delete: "));
        try {
            final int statusCode = ApiHelper.delete(context, CATEGORIES_PATH + "/" + id);
            if (statusCode == 204 || statusCode == 200) {
                System.out.println("[✓] Category deleted successfully.");
            } else {
                System.out.println("[!] Delete returned status: " + statusCode);
            }
        } catch (Exception e) {
            System.out.println("[!] Failed to delete category: " + e.getMessage());
        }
    }
}
