package com.overmind.imdbcrawler.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JSoupService implements IJSoupClient{

    @Override
    public Document initializer(String path, String language) {
        Document document = null;

        try {
            document = Jsoup.connect("https://www.imdb.com/" + path).header("Accept-Language", language).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return document;
    }
}
