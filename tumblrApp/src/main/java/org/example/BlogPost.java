package org.example;

import java.util.List;

public class BlogPost {

    private final int id;
    private final List<String> imageUrls;

    public BlogPost(int id, List<String> imageUrls) {
        this.id = id;
        this.imageUrls = imageUrls;
    }

    public List<String> getImageUrls() { return imageUrls; }
}
