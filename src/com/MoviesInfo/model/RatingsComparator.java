package src.com.MoviesInfo.model;

import java.util.Comparator;

public class RatingsComparator implements Comparator<Movie> {

    @Override
    public int compare(Movie movie1, Movie movie2) {
        return movie1.getRating().compareTo(movie2.getRating());
    }

}
