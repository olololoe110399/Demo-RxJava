package com.sun_asterisk.moviedb_50.data.source.remote.api

import com.sun_asterisk.moviedb_50.data.model.Movie
import com.sun_asterisk.moviedb_50.data.source.remote.response.GenresResponse
import com.sun_asterisk.moviedb_50.data.source.remote.response.MoviesResponse
import com.sun_asterisk.moviedb_50.utils.Constant
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRequest {

    @GET("genre/movie/list" + Constant.BASE_API_KEY + Constant.BASE_LANGUAGE)
    fun getGenres(): Observable<GenresResponse>

    @GET(
        "movie/{Category}" +
                Constant.BASE_API_KEY +
                Constant.BASE_LANGUAGE
    )
    fun getMoviesByCategory(
        @Path("Category") type: String,
        @Query("page") page: Int
    ): Observable<MoviesResponse>

    @GET(
        "discover/movie" +
                Constant.BASE_API_KEY +
                Constant.BASE_LANGUAGE
    )
    fun getMoviesByGenresID(
        @Query("with_genres") query: String,
        @Query("page") page: Int
    ): Observable<MoviesResponse>

    @GET(
        "discover/movie" +
                Constant.BASE_API_KEY +
                Constant.BASE_LANGUAGE
    )
    fun getMoviesCastID(
        @Query("with_cast") query: String,
        @Query("page") page: Int
    ): Observable<MoviesResponse>

    @GET(
        "discover/movie" +
                Constant.BASE_API_KEY +
                Constant.BASE_LANGUAGE
    )
    fun getMoviesByProduceID(
        @Query("with_companies") query: String,
        @Query("page") page: Int
    ): Observable<MoviesResponse>

    @GET("search/movie" + Constant.BASE_API_KEY + Constant.BASE_LANGUAGE)
    fun getMoviesByQuery(
        @Query("query") query: String,
        @Query("page") page: Int
    ): Observable<MoviesResponse>

    @GET(
        "movie/{movie_id}" +
                Constant.BASE_API_KEY +
                Constant.BASE_LANGUAGE +
                Constant.BASE_APPEND +
                Constant.BASE_CREDITS +
                Constant.BASE_VIDEO
    )
    fun getMovieDetails(@Path("movie_id") query: Int): Observable<Movie>
}
