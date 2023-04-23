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
    fun getAllMovies(): Flow<List<Movie>>
    //no suspend cuz of error: Dao functions that have a suspend modifier must not return a deferred/async type (kotlinx.coroutines.flow.Flow). Most probably this is an error. Consider changing the return type or removing the suspend modifier.

    @Query("SELECT * FROM movie WHERE isFavorite = 0") //get all favorits
    fun getAllFavorites(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE id =:id") //get movie by id
    fun getMovieById(id: String): Movie



}