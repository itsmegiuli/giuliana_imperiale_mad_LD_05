package com.example.movieappmad23.repositories

import com.example.movieappmad23.data.MovieDao
import com.example.movieappmad23.models.Movie

//ViewModel uses Repostitory to communicate with the Database
// (in more complex apps this is more important)

//this repository also is there because if we have 2 DB (local & remote): view model doesnt need to know which one is being used. this class handles it
class MovieRepository(private val movieDao: MovieDao) {
    fun add(movie: Movie) = movieDao.add(movie)         //insert
    fun delete(movie: Movie) = movieDao.delete(movie)   //delete
    fun update(movie: Movie) = movieDao.update(movie)   //update
    fun getAllMovies() = movieDao.getAllMovies()
    fun getAllFavorites() = movieDao.getAllFavorites()
    fun getMovieById(id: String) = movieDao.getMovieById(id)
}