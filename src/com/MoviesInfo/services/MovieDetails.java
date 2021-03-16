package src.com.MoviesInfo.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import src.com.MoviesInfo.model.InvalidMovieDetailsException;
import src.com.MoviesInfo.model.Movie;

/**
 * MovieDetails
 */
public class MovieDetails {

    private List<Movie> populateMovies(File file) {
        List<Movie> movies = new ArrayList<Movie>();
        // This try and catch block will handle the exception while reading from
        // movies file.
        // Will stop execution of program if any IO exceptions are raised.
        try {
            int counter = 0;
            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine()) {
                // This try and catch block will handle the exception while validating
                // the data of movies read from the movies file.
                // This will not stop until the EOF.
                try {

                    String data = myReader.nextLine();
                    counter++;
                    String words[] = data.split(",", 0); // Split the data seperated by commas.
                                                         // Second argument 0 means match all commas.

                    // The number of attributes for a movie should be 8.
                    if (words.length != 8) {
                        throw new InvalidMovieDetailsException("Invalid Number of Attributes at : " + counter);
                    }

                    // Movie Id should be a number.
                    if (!isValidMovieId(words[0])) {
                        throw new InvalidMovieDetailsException("Invalid Movied Id at : " + counter);
                    }

                    // Every time create a new instance of movie and insert it into movies array
                    // list.
                    Movie movie = new Movie();
                    movie.setMovieId(Integer.parseInt(words[0]));
                    movie.setMovieName(words[1]);
                    movie.setMovieType(words[2]);
                    movie.setLanguage(words[3]);
                    movie.setReleaseDate(words[4]);
                    movie.setCasting(new ArrayList<String>(List.of(words[5])));
                    movie.setRating(Double.parseDouble(words[6]));
                    movie.setTotalBusinessDone(Double.parseDouble(words[7]));
                    movies.add(movie);

                    // movie.displayMovieDetails(); // For Debug Purpose.
                    // System.out.println();

                } catch (InvalidMovieDetailsException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                    System.out.println();
                }
            }
            // close the scanner else memory leak will occur.
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace(); // Display the stack trace for debugging.
            System.out.println();
        }
        return movies;
    }

    /**
     * Check if the movie id is an non-negative integer or not.
     * 
     * @param movieId
     * @return True if valid movie id else false.
     */
    private boolean isValidMovieId(String movieId) {
        String dobRegex = "[0-9]*";
        Pattern pattern = Pattern.compile(dobRegex);

        if (movieId == null || movieId.isBlank() || !pattern.matcher(movieId).matches())
            return false;

        return true;
    }

    public List<Movie> populateMoviesFromFile(String filePath) {
        File file = new File(filePath);
        return populateMovies(file);
    }
}