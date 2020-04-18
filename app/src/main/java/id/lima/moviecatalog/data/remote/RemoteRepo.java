package id.lima.moviecatalog.data.remote;

import id.lima.moviecatalog.data.model.objects.MovieModel;
import id.lima.moviecatalog.data.model.responses.CastResponse;
import id.lima.moviecatalog.data.model.responses.GenreResponse;
import id.lima.moviecatalog.data.model.responses.MovieListResponse;
import id.lima.moviecatalog.data.model.responses.ReviewResponse;
import id.lima.moviecatalog.data.model.responses.VideoResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by muhwid on 27/10/18.
 */

public interface RemoteRepo {

    @GET("genre/movie/list")
    Call<GenreResponse> reqGenre(
            @Query("api_key") String apiKey
    );

    @GET("trending/movie/week")
    Call<MovieListResponse> reqTrendingMovie(
            @Query("page") int page,
            @Query("api_key") String apiKey
    );

    @GET("movie/now_playing")
    Call<MovieListResponse> reqNowPlaying(
            @Query("page") int page,
            @Query("api_key") String apiKey
    );

    @GET("movie/top_rated")
    Call<MovieListResponse> reqLatestMovies(
            @Query("page") int page,
            @Query("api_key") String apiKey
    );

    @GET("movie/{id_movie}")
    Call<MovieModel> reqDetailMovie(
            @Path("id_movie") int id,
            @Query("api_key") String apiKey
    );


    @GET("movie/{id_movie}/reviews")
    Call<ReviewResponse> reqReviewByMovie(
            @Path("id_movie") int id,
            @Query("page") int page,
            @Query("api_key") String apiKey
    );

    @GET("movie/{id_movie}/videos")
    Call<VideoResponse> reqMovieVideo(
            @Path("id_movie") int id,
            @Query("api_key") String apiKey
    );

    @GET("movie/{id_movie}/credits")
    Call<CastResponse> reqCastByMovie(
            @Path("id_movie") int id,
            @Query("api_key") String apiKey
    );

    @GET("discover/movie")
    Call<MovieListResponse> reqMovieByGenre(
            @Query("page") int page,
            @Query("api_key") String apiKey,
            @Query("with_genres") int genreId
    );

    @GET("search/movie")
    Call<MovieListResponse> reqSearchMovie(
            @Query("page") int page,
            @Query("query") String query,
            @Query("api_key") String apiKey
    );

}
