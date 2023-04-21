package com.example.movieappmad23.data
import androidx.room.*

import com.example.movieappmad23.models.Movie;
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    // insert, delete, update: pre-defined annotations
    //CRUD (Create, Read, Update, Delete)
    @Insert
    fun add (movie: Movie)

    @Delete
    fun delete (movie: Movie)

    @Update
    fun update (movie: Movie)

    @Query("SELECT * FROM movie") // get all movies
    fun getAllMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM movie where isFavorite = true") //get all favorits
    fun getAllFavorites(): List<Movie>

    @Query("SELECT * FROM movie where id =:id") //get movie by id
    fun getMovieById(id: String): Movie


}