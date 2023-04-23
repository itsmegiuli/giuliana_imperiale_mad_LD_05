package com.example.movieappmad23.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    lateinit var details : Flow<Movie>
    //'lateinit' allows initializing a not-null property outside of a constructor

   fun getMovieById(id: String): Movie {
            return movieRepository.getMovieById(id)
   }
    suspend fun updateFavMovie(movie: Movie) {
        movie.isFavorite = !movie.isFavorite
        movieRepository.update(movie)
    }
}

/*

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



/*
    init {
        viewModelScope.launch {
            //db operations are async, we need threats for that

            //read data through repository
            //everytime the list has changed in the DB (that's why we use flow), it will be collected
            repository.getAllMovies().collect{ movieList ->
                if(!movieList.isNullOrEmpty()) { //only collect if not null
                    _movieListState.value = movieList

                }

            }
        }
    }

    var movieUiState by mutableStateOf(AddMovieUiState())
        private set

    val favoriteMovies: Flow<List<Movie>> = _movieListState.asStateFlow()

    init {
        _movieListState.value = getMovies()
    }

    fun updateUIState(newMovieUiState: AddMovieUiState, event: AddMovieUIEvent){
        var state = AddMovieUiState()   // this is needed because copy always creates a new instance

        when (event) {
            is AddMovieUIEvent.TitleChanged -> {
                val titleResult = Validator.validateMovieTitle(newMovieUiState.title)
                state = if(!titleResult.successful) newMovieUiState.copy(titleErr = true) else newMovieUiState.copy(titleErr = false)
            }
            is AddMovieUIEvent.YearChanged -> {
                val yearResult = Validator.validateMovieYear(newMovieUiState.year)
                state = if(!yearResult.successful) newMovieUiState.copy(yearErr = true) else newMovieUiState.copy(yearErr = false)
            }

            is AddMovieUIEvent.DirectorChanged -> {
                val directorResult = Validator.validateMovieDirector(newMovieUiState.director)
                state = if(!directorResult.successful) newMovieUiState.copy(directorErr = true) else newMovieUiState.copy(directorErr = false)
            }

            is AddMovieUIEvent.ActorsChanged -> {
                val actorsResult = Validator.validateMovieActors(newMovieUiState.actors)
                state = if(!actorsResult.successful) newMovieUiState.copy(actorsErr = true) else newMovieUiState.copy(actorsErr = false)
            }

            is AddMovieUIEvent.RatingChanged -> {
                val ratingResult = Validator.validateMovieRating(newMovieUiState.rating)
                state = if(!ratingResult.successful) newMovieUiState.copy(ratingErr = true) else newMovieUiState.copy(ratingErr = false)
            }

            is AddMovieUIEvent.GenresChanged -> {
                val genreResult = Validator.validateMovieGenres(newMovieUiState.genre)
                state = if(!genreResult.successful) newMovieUiState.copy(genreErr = true) else newMovieUiState.copy(genreErr = false)
            }
            else -> {}
        }

        movieUiState = state.copy(actionEnabled = !newMovieUiState.hasError())
    }

    fun updateFavoriteMovies(movie: Movie) = _movieListState.value.find { it.id == movie.id }?.let { movie ->
        movie.isFavorite = !movie.isFavorite
    }


    suspend fun saveMovie() {
        val movie = movieUiState.toMovie()

        repository.add(movie)
    }

    suspend fun deleteMovie(movie: Movie) {
        repository.delete(movie)
    }

    suspend fun getFavoriteMovies() : Flow<List<Movie>> {
        return repository.getAllFavorites()
    }
}


 */
 */