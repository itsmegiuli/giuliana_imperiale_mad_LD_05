package com.example.movieappmad23.repositories

import com.example.movieappmad23.data.MovieDao
import com.example.movieappmad23.models.Movie
import kotlinx.coroutines.flow.Flow

//ViewModel uses Repostitory to communicate with the Database
// (in more complex apps this is more important)

//this repository also is there because if we have 2 DB (local & remote): view model doesnt need to know which one is being used. this class handles it
class MovieRepository(private val movieDao: MovieDao) {
    suspend fun add(movie: Movie) = movieDao.add(movie)         //insert
    suspend fun delete(movie: Movie) = movieDao.delete(movie)   //delete
    suspend fun update(movie: Movie) = movieDao.update(movie)   //update
    suspend fun getAllMovies(): Flow<List<Movie>> = movieDao.getAllMovies()
    suspend fun getAllFavorites(): Flow<List<Movie>> = movieDao.getAllFavorites()
    suspend fun getMovieById(id: String) = movieDao.getMovieById(id)
}