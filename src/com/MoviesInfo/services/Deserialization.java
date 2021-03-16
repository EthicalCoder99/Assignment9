package src.com.MoviesInfo.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import src.com.MoviesInfo.model.Movie;

public class Deserialization {
    public List<Movie> deserialize(String fileName) {
        List<Movie> movieList = null;
        File file = new File(fileName);
        try (FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectOutputStream = new ObjectInputStream(fileInputStream);) {

            movieList = (List<Movie>) objectOutputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("File not able to convert to file output stream.");
            e.printStackTrace();
        }
        return movieList;
    }
}
