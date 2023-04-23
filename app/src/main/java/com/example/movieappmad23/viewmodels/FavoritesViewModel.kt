package com.example.movieappmad23.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(private val movieRepository: MovieRepository): ViewModel() {
// inherits from ViewModel class

    private val _favMovieListState = MutableStateFlow(listOf<Movie>())
    val favMovieListState: StateFlow<List<Movie>> = _favMovieListState.asStateFlow()

    init {
        viewModelScope.launch {
           movieRepository.getAllFavorites().collect{ movieList ->
               _favMovieListState.value = movieList

            }
        }
    }


    suspend fun updateFavMovie(movie: Movie) {
        movie.isFavorite = !movie.isFavorite
        movieRepository.update(movie)
    }
}
