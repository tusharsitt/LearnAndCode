package org.example;

import java.util.List;

public class TumblrAppController {
    private final IApiClient apiClient;
    private final IParser parser;
    private final IView view;

    public TumblrAppController(IApiClient apiClient, IParser parser, IView view) {
        this.apiClient = apiClient;
        this.parser = parser;
        this.view = view;
    }

    public void run() {

        String blogName = view.getBlogName();
        if (blogName == null || blogName.isEmpty()) {
            view.displayError("Invalid blog name.");
            return;
        }

        int[] range = view.getRange();
        if (range == null || range.length != 2 || range[0] > range[1] || range[0] < 1) {
            view.displayError("Invalid range format. Use format '1-5'.");
            return;
        }

        int startInput = range[0];
        int endInput = range[1];

        // Logic Calculation
        // API 'start' is the offset (0-indexed). API 'num' is count.
        // User wants 1-5. Logic: Start index = 0, Count = 5.
        int apiStart = startInput - 1;
        int apiNum = endInput - startInput + 1;

        try {
            String jsonResponse = apiClient.fetchData(blogName, apiStart, apiNum);
            BlogInfo info = parser.parseBlogInfo(jsonResponse);
            List<BlogPost> posts = parser.parsePosts(jsonResponse);

            view.displayBlogInfo(info);
            view.displayPosts(posts, startInput);

        } catch (Exception exception) {
            view.displayError("An error occurred: " + exception.getMessage());
            exception.printStackTrace();
        }
    }
}
