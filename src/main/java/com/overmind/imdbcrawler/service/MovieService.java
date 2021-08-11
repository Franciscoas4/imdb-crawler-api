package com.overmind.imdbcrawler.service;

import com.overmind.imdbcrawler.entity.Comments;
import com.overmind.imdbcrawler.entity.Movie;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.overmind.imdbcrawler.util.CheckUtils.checkGreaterEqualThanFive;
import static com.overmind.imdbcrawler.util.CheckUtils.checkHasRating;

@Service
public class MovieService {

    @Autowired
    private JSoupService jSoupService;

    public List<Movie> listMovies(String path,String language) {
        Document dom = jSoupService.initializer(path, language);
        Elements bodyTable = dom.select("tbody[class='lister-list']");
        List<Movie> moviesList = addMovieNamesAndRating(bodyTable);
        List <Movie> topTenMoviesList =  moviesList.subList(0, 10);
        Collections.reverse(topTenMoviesList);
        return addMovieDetails(topTenMoviesList);
    }

    private List<Movie> addMovieDetails(List<Movie> topTenMoviesList) {
        List<Movie> movieListDetails= new ArrayList<>();

        for (Movie movie: topTenMoviesList) {
            Document dom = jSoupService.initializer(movie.getUrl(), "en");
            Elements directorsMovie = dom.select("div[class='ipc-metadata-list-item__content-container']");
            movie.setDirectors(Objects.requireNonNull(directorsMovie.first()).getElementsByAttribute("href").html());
            Elements castTable = dom.select("div[class='StyledComponents__CastItemWrapper-y9ygcu-7 hTEaNu']");
            addCastToMovie(castTable,movie);
            addCommentToMovie(movie);
            movieListDetails.add(movie);
        }

        return movieListDetails;

    }

    private List<Movie> addMovieNamesAndRating(Elements bodyTable) {
        List<Movie> moviesList = new ArrayList<>();

        for (Element trElement : bodyTable.select("tr")) {
            Movie movie = new Movie();
            movie.setId(Integer.parseInt(trElement.getElementsByTag("span").val("rk").attr("data-value")));
            movie.setName(trElement.getElementsByClass("titleColumn").get(0).getElementsByAttribute("href").text());
            movie.setUrl(trElement.getElementsByClass("posterColumn").get(0).selectFirst("a").attr("href"));
            movie.setRate(trElement.getElementsByClass("ratingColumn imdbRating").text());
            moviesList.add(movie);
        }

        return moviesList;
    }

    private void addCastToMovie(Elements castTable,Movie movie) {
        for (Element castElement: castTable.select("div")) {
            movie.getCast().put(castElement.getElementsByAttributeValue("data-testid", "title-cast-item__actor").text(), castElement.getElementsByAttributeValue("class", "StyledComponents__CharacterNameWithAs-y9ygcu-4 gyGbpY").text());
        }
    }

    private void addCommentToMovie(Movie movie) {
        String [] path = movie.getUrl().split("/");
        Document dom = jSoupService.initializer("title/"+path[2]+"/reviews?ref_=tt_urv", "en");
        Elements commentsDiv = dom.select("div[class='lister-list']");
        List<Comments> commentsList = new ArrayList<Comments>();

        for(Element element : commentsDiv.select("div[class^='lister-item mode-detail imdb-user-review ']")) {
            Comments comments = new Comments();
            if(checkHasRating(element)) {
                Double rate = convertRateToNumber(element.getElementsByClass("ipl-ratings-bar").text());
                if(checkGreaterEqualThanFive(rate)) {
                    comments = addComments(element,rate);
                    commentsList.add(comments);
                }
            }

        }
        movie.setComments(commentsList);
    }

    private Comments addComments(Element element, Double rate) {
        Comments comments = new Comments();
        comments.setTitle(element.getElementsByClass("lister-item-content").get(0).getElementsByClass("title").text());
        comments.setContent(element.getElementsByClass("text show-more__control").text());
        comments.setRate(rate);
        comments.setAuthor(element.getElementsByClass("display-name-link").text());
        return comments;
    }

    private Double convertRateToNumber(String rate) {
        String [] arrayValues = rate.split("/");
        Double number = Double.parseDouble(arrayValues[0]);
        return number;
    }

}
