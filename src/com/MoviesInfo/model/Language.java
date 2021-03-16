package src.com.MoviesInfo.model;

import java.io.Serializable;

public class Language implements Serializable {
    private static final long serialVersionUID = 1L;
    private String language;

    public Language(String language) {
        this.language = language.toUpperCase();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language.toUpperCase();
    }

    @Override
    public String toString() {
        return this.language;
    }

}
