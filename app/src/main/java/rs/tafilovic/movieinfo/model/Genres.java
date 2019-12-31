package rs.tafilovic.movieinfo.model;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Genres {

    @SerializedName("genres")
    @Expose
    private List<GenreItem> genres = null;

    public List<GenreItem> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreItem> genres) {
        this.genres = genres;
    }
}
