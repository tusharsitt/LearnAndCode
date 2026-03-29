package org.example;

import java.util.List;

public interface IView {
    String getBlogName();
    int[] getRange();
    void displayBlogInfo(BlogInfo info);
    void displayPosts(List<BlogPost> posts, int startNumber);
    void displayError(String message);
}
