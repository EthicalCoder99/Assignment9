package src;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import src.com.MoviesInfo.services.MoviesDbServices;

public class App {

    private final static String moviesFileRelativePath = "src/com/MoviesInfo/resources/Movies.txt";
    private final static String serializedMoviesFilePath = "src/com/MoviesInfo/resources/MoviesSerialized.txt";

    public static void main(String[] args) throws Exception {
        // MovieCollection mCollection = new MovieCollection(moviesFileRelativePath);
        // List<Movie> movieList = mCollection.getMovieList();
        // Serialization serialization = new Serialization();
        // Deserialization deserialization = new Deserialization();

        // serialization.serialize(movieList, serializedMoviesFilePath);
        // List<Movie> dMovies = deserialization.deserialize(serializedMoviesFilePath);

        // for (Movie movie : dMovies) {
        // System.out.println(movie);
        // }
        // System.out.println("\n\n*******************************************\n\n");
        // for (Movie movie : mCollection.getMoviesRealeasedInYear(2010)) {
        // System.out.println(movie);
        // }

        // MovieListServices mServices = new MovieListServices();
        // for (Movie movie : mServices.getMoviesByBusinessDone(movieList, 4.0)) {
        // System.out.println(movie);
        // }

        // String date = "2000-10-23";
        // DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        // DateFormat dateFormatter2 = new SimpleDateFormat("dd/MM/yyyy");
        // Date d = dateFormatter.parse(date);
        // String d2 = dateFormatter2.format(d);
        // System.out.println(d2);

        MoviesDbServices mDbServices = new MoviesDbServices();
        mDbServices.readMoviesFromFile(moviesFileRelativePath);
        mDbServices.displayMovies();
    }
}
