package src.com.MoviesInfo.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import src.com.MoviesInfo.model.Movie;
import src.com.MoviesInfo.model.RatingsComparator;

public class MovieListServices {

    private static RatingsComparator ratingsComparator = new RatingsComparator();

    public void updateRatings(String movieName, double rating, List<Movie> movieList) {
        for (Movie movie : movieList) {
            if (movie.getMovieName().equals(movieName)) {
                movie.setRating(rating);
            }
        }
    }

    public void updateBusinessDone(String movieName, double amount, List<Movie> movieList) {
        for (Movie movie : movieList) {
            if (movie.getMovieName().equals(movieName)) {
                movie.setTotalBusinessDone(amount);
            }
        }
    }

    public List<Movie> orderByRatings(List<Movie> movieList) {
        List<Movie> resultList = new ArrayList<Movie>(movieList);
        Collections.sort(resultList, ratingsComparator);
        return resultList;
    }

    public List<Movie> getMoviesByBusinessDone(List<Movie> movieList, double amount) {
        List<Movie> resultList = new ArrayList<Movie>();
        for (Movie movie : movieList) {
            if (movie.getTotalBusinessDone() >= amount) {
                resultList.add(movie);
            }
        }
        return orderByRatings(resultList);
    }
}
