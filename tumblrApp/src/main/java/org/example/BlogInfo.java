package org.example;

public class BlogInfo {
    private final String title;
    private final String name;
    private final String description;
    private final int totalPosts;

    public BlogInfo(String title, String name, String description, int totalPosts) {
        this.title = title;
        this.name = name;
        this.description = description;
        this.totalPosts = totalPosts;
    }

    public String getTitle() { return title; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getTotalPosts() { return totalPosts; }
}
