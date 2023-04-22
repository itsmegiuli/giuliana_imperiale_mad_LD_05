package com.example.movieappmad23.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieappmad23.repositories.MovieRepository

class MoviesViewModelFactory(private val repository: MovieRepository): ViewModelProvider.Factory { //inherits from ViewModelProvider.Factory
    override fun <T : ViewModel> create(modelClass: Class<T>): T { //for us to be able to make an instance of MovieViewModel with a parameter
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {//should only work with MovieRepostiory, if not throw exception
            return MoviesViewModel(repository) as T //casted as T
        }
        else throw IllegalArgumentException("Unknown ViewModel class")
    }
}