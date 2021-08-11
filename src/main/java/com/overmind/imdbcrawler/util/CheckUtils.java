package com.overmind.imdbcrawler.util;

import org.jsoup.nodes.Element;

public class CheckUtils {

    public static Boolean checkHasRating(Element element) {
        return element.getElementsByClass("ipl-ratings-bar").hasText() ? true : false;
    }

    public static Boolean checkGreaterEqualThanFive(Double number) {
        return number >4 ? true : false;
    }
}
