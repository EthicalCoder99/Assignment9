package src.com.MoviesInfo.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import src.com.MoviesInfo.model.Movie;

public class MoviesDAO {

	private Connection conn = null;

	public MoviesDAO(Connection conn) {
		this.conn = conn;
	}

	private String toValueString(Movie movie) {
		String res = " (";
		res += "\"" + movie.getMovieId() + "\", ";
		res += "\"" + movie.getMovieName() + "\", ";
		res += "\"" + movie.getMovieTypeAsString() + "\", ";
		res += "\"" + movie.getLanguageAsString() + "\", ";
		res += "\"" + this.formatToSqlDate(movie.getReleaseDateAsString()) + "\", ";
		res += "\"" + this.convertListToString(movie.getCasting()) + "\", ";
		res += "\"" + movie.getRating() + "\", ";
		res += "\"" + movie.getTotalBusinessDone() + "\")";
		return res;
	}

	private Movie convertToMovieObj(ResultSet rs) {
		Movie movie = new Movie();
		try {
			movie.setMovieId(rs.getInt(1));
			movie.setMovieName(rs.getString(2));
			movie.setMovieType(rs.getString(3));
			movie.setLanguage(rs.getString(4));
			movie.setReleaseDate(this.formatToMovieDate(rs.getString(5)));
			movie.setCasting(this.convertStringToList(rs.getString(6)));
			movie.setRating(rs.getDouble(7));
			movie.setTotalBusinessDone(rs.getDouble(8));
		} catch (SQLException e) {
			System.out.println("Error while converting to movie object." + e);
		}
		return movie;
	}

	private String formatToMovieDate(String date) {
		String result = "";
		DateFormat sqlDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat movieDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date sqlDate = sqlDateFormatter.parse(date);
			result = movieDateFormatter.format(sqlDate);
		} catch (ParseException e) {
			System.out.println("Error while parsing sqlDate.");
			e.printStackTrace();
		}
		return result;
	}

	private String formatToSqlDate(String date) {
		String result = "";
		DateFormat sqlDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat movieDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date sqlDate = movieDateFormatter.parse(date);
			result = sqlDateFormatter.format(sqlDate);
		} catch (ParseException e) {
			System.out.println("Error while parsing sqlDate.");
			e.printStackTrace();
		}
		return result;
	}

	private List<String> convertStringToList(String s) {
		return new ArrayList<String>(Arrays.asList(s.split(",")));
	}

	private String convertListToString(List<String> list) {
		String result = "";
		for (int i = 0; i < list.size() - 1; i++) {
			result += list.get(i) + ",";
		}
		result += list.get(list.size() - 1);
		return result;
	}

	public void insertMovie(Movie movie) {
		try {
			Statement stmt = this.conn.createStatement();
			String insertStmt = "INSERT INTO movies VALUES" + this.toValueString(movie);
			stmt.executeUpdate(insertStmt);
		} catch (SQLException e) {
			System.out.println("Error while inserting movie data." + e);
		}
	}

	public Movie getMovieByName(String movieName) {
		Movie movie = null;
		try {
			Statement stmt = this.conn.createStatement();
			String query = "SELECT * FROM movies WHERE name=" + movieName;
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				movie = this.convertToMovieObj(rs);
			}

		} catch (Exception e) {
			System.out.println("Error while fetching movie data." + e);
		}
		return movie;
	}

	public List<Movie> getMovies() {
		List<Movie> movieList = new ArrayList<Movie>();
		try {

			Statement stmt = this.conn.createStatement();
			String query = "SELECT * FROM movies";
			ResultSet rs = stmt.executeQuery(query);
			Movie movie = null;
			while (rs.next()) {
				movie = this.convertToMovieObj(rs);
				movieList.add(movie);
			}

		} catch (Exception e) {
			System.out.println("Error while fetching movies and creating movie list." + e);
		}
		return movieList;
	}

	public void deleteMovieById(int id) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM movies WHERE id=" + id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error while deleting movies." + e);
		}

	}

	public List<Movie> getTodayMovie() {
		List<Movie> movieList = new ArrayList<Movie>();
		try {

			Statement stmt = conn.createStatement();
			String qry = "SELECT * FROM movies WHERE releaseDate = CURDATE()";
			ResultSet rs = stmt.executeQuery(qry);
			Movie movie = null;
			while (rs.next()) {
				movie = this.convertToMovieObj(rs);
				movieList.add(movie);
			}

		} catch (Exception e) {
			System.out.println("Error while fetching movies data that are releasing today." + e);
		}

		return movieList;
	}

	public List<Movie> sortMoviesByLanguage() {
		List<Movie> movieList = new ArrayList<Movie>();
		try {

			Statement stmt = conn.createStatement();
			String qry = "SELECT * FROM movies ORDER BY language";
			ResultSet rs = stmt.executeQuery(qry);
			Movie movie = null;
			while (rs.next()) {
				movie = this.convertToMovieObj(rs);
				movieList.add(movie);
			}

		} catch (Exception e) {
			System.out.println("Error while fetching movies data by language." + e);
		}

		return movieList;
	}

	public void createTables() {
		String createMoviesTable = "CREATE TABLE IF NOT EXISTS movies (id INT NOT NULL, name VARCHAR(50), category VARCHAR(20), language VARCHAR(20), releaseDate DATE, casting TEXT, rating DOUBLE, totalBusinessDone DOUBLE, PRIMARY KEY(id))";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(createMoviesTable);
		} catch (SQLException e) {
			System.out.println("Error while creating movies tables." + e);
		}
	}

	public void dropTables() {
		String dropMoviesTable = "DROP TABLE movies";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(dropMoviesTable);
		} catch (SQLException e) {
			System.out.println("Error while dropping movies table." + e);
		}
	}

}

// /**
// * Table schemas.
// * movie(id, name, language, releaseDate);
// * shows(id, movieid, showTime, availableTickets, ticketPrice);
// */