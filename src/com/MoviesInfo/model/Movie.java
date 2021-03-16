package src.com.MoviesInfo.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Movie
 */
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;
    private int movieId;
    private String movieName;
    private Category movieType;
    private Language language;
    private Date releaseDate;
    private List<String> casting;
    private Double rating;
    private Double totalBusinessDone;

    private static DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

    public Movie() {
    }

    public Movie(int movieId, String movieName, Category movieType, Language language, String releaseDate,
            List<String> casting, Double rating, Double totalBusinessDone) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieType = movieType;
        this.language = language;
        setReleaseDate(releaseDate);
        this.casting = casting;
        this.rating = rating;
        this.totalBusinessDone = totalBusinessDone;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Category getMovieType() {
        return movieType;
    }

    public String getMovieTypeAsString() {
        return movieType.getCategory();
    }

    public void setMovieType(String movieType) {
        this.movieType = new Category(movieType);
    }

    public Language getLanguage() {
        return language;
    }

    public String getLanguageAsString() {
        return language.getLanguage();
    }

    public void setLanguage(String language) {
        this.language = new Language(language);
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getReleaseDateAsString() {
        return dateFormatter.format(this.releaseDate);
    }

    public void setReleaseDate(String releaseDate) {
        Date rDate = null;
        try {
            rDate = dateFormatter.parse(releaseDate);
        } catch (ParseException e) {
            System.out.println("Error occured while assigning releaseDate. (setReleaseDate())");
            e.printStackTrace();
        }
        this.releaseDate = rDate;
    }

    public List<String> getCasting() {
        return casting;
    }

    public void setCasting(List<String> casting) {
        this.casting = casting;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getTotalBusinessDone() {
        return totalBusinessDone;
    }

    public void setTotalBusinessDone(Double totalBusinessDone) {
        this.totalBusinessDone = totalBusinessDone;
    }

    @Override
    public String toString() {
        String result = "";
        result += "\nId : " + this.getMovieId();
        result += "\nName : " + this.getMovieName();
        result += "\nType : " + this.getMovieType();
        result += "\nLanguage : " + this.getLanguage();
        result += "\nRelease Date : " + this.getReleaseDateAsString();
        result += "\nCasting : " + this.getCasting().toString();
        result += "\nRating : " + this.getRating();
        result += "\nTotal Business Done : " + this.getTotalBusinessDone();
        return result;
    }
}