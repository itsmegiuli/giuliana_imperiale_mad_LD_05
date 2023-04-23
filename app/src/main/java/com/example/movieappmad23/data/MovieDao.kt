package com.example.movieappmad23.data
import androidx.room.*

import com.example.movieappmad23.models.Movie;
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    // insert, delete, update: pre-defined annotations
    //CRUD (Create, Read, Update, Delete)
    @Insert
    suspend fun add (movie: Movie)

    @Delete
    suspend fun delete (movie: Movie)

    @Update
    suspend fun update (movie: Movie)

    @Query("SELECT * FROM movie") // get all movies
    suspend fun getAllMovies(): List<Movie> //Flow<List<Movie>>

    @Query("SELECT * FROM movie where isFavorite = true") //get all favorits
    suspend fun getAllFavorites(): List<Movie>

    @Query("SELECT * FROM movie where id =:id") //get movie by id
    suspend fun getMovieById(id: String): Movie


}