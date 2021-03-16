package src.com.MoviesInfo.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import src.com.MoviesInfo.model.Movie;

public class Serialization {
    public void serialize(List<Movie> movies, String fileName) {
        File file = new File(fileName);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);) {

            objectOutputStream.writeObject(movies); // Serializing whole list of movies.

        } catch (IOException e) {
            System.out.println("File not able to convert to file output stream.");
            e.printStackTrace();
        }

    }
}
