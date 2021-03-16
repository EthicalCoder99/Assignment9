package src.com.MoviesInfo.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import src.com.MoviesInfo.services.MovieDetails;

public class MovieCollection implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Movie> movieList;

    public MovieCollection(String filePath) {
        MovieDetails movieDetails = new MovieDetails();
        setMovieList(movieDetails.populateMoviesFromFile(filePath));
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public void addMovie(Movie movie) {
        this.movieList.add(movie);
    }

    public void deleteMovie(String movieName) {
        List<Movie> deletionList = new ArrayList<Movie>();
        for (Movie movie : this.movieList) {
            if (movie.getMovieName().equals(movieName)) {
                deletionList.add(movie);
            }
        }

        for (Movie movie : deletionList) {
            if (this.movieList.contains(movie)) {
                this.movieList.remove(movie);
            }
        }
    }

    public List<Movie> getMoviesRealeasedInYear(int year) {
        List<Movie> resultList = new ArrayList<Movie>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        for (Movie movie : this.movieList) {
            int movieReleaseYear = Integer.parseInt(df.format(movie.getReleaseDate()));
            if (movieReleaseYear == year) {
                resultList.add(movie);
            }
        }

        return resultList;
    }

    public List<Movie> getMoviesByActor(String actorName) {
        List<Movie> resultList = new ArrayList<Movie>();
        for (Movie movie : this.movieList) {
            for (String actor_name : movie.getCasting()) {
                System.out.println(actor_name);
                if (actor_name.equals(actorName)) {
                    resultList.add(movie);
                    break;
                }
            }
        }
        return resultList;
    }

}
