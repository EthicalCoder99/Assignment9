package src.com.MoviesInfo.model;

import java.io.Serializable;

public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    private String category;

    public Category(String category) {
        this.category = category.toUpperCase();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category.toUpperCase();
    }

    @Override
    public String toString() {
        return this.category;
    }

}
