package rs.tafilovic.movieinfo.rest;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import rs.tafilovic.movieinfo.model.Casts;
import rs.tafilovic.movieinfo.model.Genres;
import rs.tafilovic.movieinfo.model.Movies;

/**
 * @author Semsudin Tafilovic
 *
 * This class extends {@link BaseController} and it is resposible for making actual API calls via Retrofit
 * All calls returns {@link Movies} instance
 */
public class MoviesController extends BaseController {

    public MoviesController(){
        super();
    }

    public io.reactivex.Observable<Movies> getTopRated(int page) {
        return retrofit.create(MovieApi.class).getTopRated(page);
    }

    public io.reactivex.Observable<Movies> getPopular(int page){
        return retrofit.create(MovieApi.class).getPopular(page);
    }

    public io.reactivex.Single<Casts> getCredits(int movieId){
        return retrofit.create(MovieApi.class).getCredits(movieId);
    }

    public io.reactivex.Observable<Movies> searchMovies(int page, String queryToSearch, boolean includeAdult){
        return retrofit.create(MovieApi.class).searchMovies(page, queryToSearch, includeAdult);
    }

    public io.reactivex.Observable<Genres> getGenres(){
        return retrofit.create(MovieApi.class).getGenres();
    }
}