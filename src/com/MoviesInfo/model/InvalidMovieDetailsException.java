package src.com.MoviesInfo.model;

public class InvalidMovieDetailsException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidMovieDetailsException(String s) {
        super(s);
    }
}
