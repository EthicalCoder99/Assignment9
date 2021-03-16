package src.com.MoviesInfo.services;

import java.sql.Connection;
import java.util.List;

import src.com.MoviesInfo.model.Movie;
import src.com.MoviesInfo.model.MovieCollection;
import src.com.MoviesInfo.util.DbConnection;
import src.com.MoviesInfo.util.MoviesDAO;

public class MoviesDbServices {

    final private String username = "sentinel";
    final private String password = "sweettooth";
    final private String database = "javadb";

    private DbConnection dbConnection;
    private Connection conn;
    private MoviesDAO moviesDAO;

    public MoviesDbServices() {
        this.dbConnection = new DbConnection(username, password, database);
        this.conn = dbConnection.getConnection();
        this.moviesDAO = new MoviesDAO(conn);
    }

    public void readMoviesFromFile(String filePath) {

        moviesDAO.dropTables();
        moviesDAO.createTables();

        MovieCollection movie_collection = new MovieCollection(filePath);

        List<Movie> movieList = movie_collection.getMovieList();

        for (Movie movie : movieList) {
            moviesDAO.insertMovie(movie);
        }
    }

    public void displayMovies() {
        List<Movie> movieList = moviesDAO.getMovies();
        for (Movie movie : movieList) {
            System.out.println(movie);
        }
    }

}