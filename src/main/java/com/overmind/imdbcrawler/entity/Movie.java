package com.overmind.imdbcrawler.entity;

import java.util.HashMap;
import java.util.List;

public class Movie {

    private Integer id;
    private String name;
    private String rate;
    private String directors;
    private String url;

    private HashMap<String, String> cast = new HashMap<>();

    private List<Comments> comments;


    public Movie() {}

    public Movie(Integer id, String name, String rate, String directors, String url, HashMap<String, String> cast, List<Comments> comments) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.directors = directors;
        this.url = url;
        this.cast = cast;
        this.comments = comments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HashMap<String, String> getCast() {
        return cast;
    }

    public void setCast(HashMap<String, String> cast) {
        this.cast = cast;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

}
