package rs.tafilovic.movieinfo.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rs.tafilovic.movieinfo.model.Casts;
import rs.tafilovic.movieinfo.model.Genres;
import rs.tafilovic.movieinfo.model.Movies;

/**
 * @author Semsudin Tafilovic
 * This class describes API Endpoints to get list of movies
 */
public interface MovieApi {

    @GET("/genre/movie/list")
    io.reactivex.Observable<Genres> getGenres();

    @GET("movie/top_rated")
    io.reactivex.Observable<Movies> getTopRated(@Query("page") int number);

    @GET("movie/popular")
    io.reactivex.Observable<Movies> getPopular(@Query("page") int number);

    @GET("movie/{movie_id}/credits")
    io.reactivex.Single<Casts> getCredits(@Path("movie_id") int movie_id);

    @GET("movie/top_rated")
    io.reactivex.Observable<Movies> searchMovies(@Query("page") int number, @Query("query") String query, @Query("include_adult") boolean includeAdult);
}
