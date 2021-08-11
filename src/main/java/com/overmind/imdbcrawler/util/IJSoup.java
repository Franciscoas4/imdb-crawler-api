package com.overmind.imdbcrawler.util;

import org.jsoup.nodes.Document;

public interface IJSoup<T> {
    public Document initializer(String path, String language);
}
