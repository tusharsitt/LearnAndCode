package org.example;

import java.util.List;
import java.util.Scanner;

public class ConsoleView implements IView {
    private final Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }


    public void printLine() {
        System.out.println("-----------------------------------------------------------------------");
    }

    @Override
    public String getBlogName() {
        System.out.println("Enter the Tumblr blog name:");
        return scanner.nextLine();
    }

    @Override
    public int[] getRange() {
        System.out.println("Enter the range (ex: 1-5):");
        String line = scanner.nextLine();

        try {
            String[] parts = line.split("-");
            int start = Integer.parseInt(parts[0].trim());
            int end = Integer.parseInt(parts[1].trim());
            return new int[]{start, end};
        } catch (Exception e) {
            return null; // Controller handles the error
        }
    }

    @Override
    public void displayBlogInfo(BlogInfo info) {
        System.out.println("Title: " + info.getTitle());
        System.out.println("Name: " + info.getName());
        System.out.println("Description: " + info.getDescription().replace("\n", " "));
        System.out.println("No of posts: " + info.getTotalPosts());
        printLine();
    }

    @Override
    public void displayPosts(List<BlogPost> posts, int startNumber) {
        int currentCounter = startNumber;

        for (BlogPost post : posts) {
            List<String> urls = post.getImageUrls();
            if (urls.isEmpty()) continue;

            // Print first image with the numbering
            System.out.println(currentCounter + ". " + urls.get(0));

            // Print extra images just indented slightly
            for (int index = 1; index < urls.size(); index++) {
                System.out.println("   " + urls.get(index));
            }
            currentCounter++;
        }
    }

    @Override
    public void displayError(String message) {
        System.out.println("ERROR: " + message);
    }
}