package org.example;

import java.util.List;

public interface IParser {
    BlogInfo parseBlogInfo(String jsonResponse) throws Exception;
    List<BlogPost> parsePosts(String jsonResponse) throws Exception;
}
