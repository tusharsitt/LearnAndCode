package org.example;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;


class TumblrJsonParser implements IParser {
    private final ObjectMapper mapper;

    public TumblrJsonParser() {
        this.mapper = new ObjectMapper();
    }

    @Override
    public BlogInfo parseBlogInfo(String jsonResponse) throws Exception {
        JsonNode root = mapper.readTree(jsonResponse);
        JsonNode tumblelog = root.path("tumblelog");

        String title = getTextOrDefault(tumblelog, "title", "No Title");
        String name = getTextOrDefault(tumblelog, "name", "No Name");
        String description = getTextOrDefault(tumblelog, "description", "No Description");

        // Strip HTML tags from description for cleaner output
        description = description.replaceAll("\\<.*?\\>", "");
        int total = root.path("posts-total").asInt(0);
        return new BlogInfo(title, name, description, total);
    }

    @Override
    public List<BlogPost> parsePosts(String jsonResponse) throws Exception {
        List<BlogPost> result = new ArrayList<>();
        JsonNode root = mapper.readTree(jsonResponse);
        JsonNode postsArray = root.path("posts");

        if (postsArray.isArray()) {
            int index = 0;
            for (JsonNode node : postsArray) {
                BlogPost post = createBlogPostFromNode(node, index);
                if (post != null) {
                    result.add(post);
                }
                index++;
            }
        }
        return result;
    }

    private BlogPost createBlogPostFromNode(JsonNode postNode, int index) {
        List<String> imageUrls = extractUrlsFromPostNode(postNode);

        if (imageUrls.isEmpty()) {
            return null;
        }
        return new BlogPost(index, imageUrls);
    }

    private List<String> extractUrlsFromPostNode(JsonNode postNode) {
        List<String> images = new ArrayList<>();
        JsonNode photosNode = postNode.path("photos");

        if (photosNode.isArray() && !photosNode.isEmpty(null)) {
            for (JsonNode photo : photosNode) {
                String url = resolveBestImageUrl(photo);
                if (url != null) images.add(url);
            }
        }
        else {
            String url = resolveBestImageUrl(postNode);
            if (url != null) images.add(url);
        }
        return images;
    }

    private String resolveBestImageUrl(JsonNode node) {
        // We look for 1280 first, then 500
        String url1280 = getTextOrDefault(node, "photo-url-1280", null);
        if (url1280 != null) return url1280;

        String url500 = getTextOrDefault(node, "photo-url-500", null);
        if (url500 != null) return url500;

        return null;
    }

    private String getTextOrDefault(JsonNode parent, String fieldName, String defaultValue) {
        JsonNode node = parent.path(fieldName);

        if (node.isMissingNode() || node.isNull()) {
            return defaultValue;
        }

        return node.asString(defaultValue);
    }
}