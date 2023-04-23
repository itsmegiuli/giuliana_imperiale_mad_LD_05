package com.example.movieappmad23.utils

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.example.movieappmad23.data.MovieDatabase
import com.example.movieappmad23.repositories.MovieRepository
import com.example.movieappmad23.viewmodels.DetailsViewModelFactory
import com.example.movieappmad23.viewmodels.FavoritesViewModelFactory
import com.example.movieappmad23.viewmodels.MoviesViewModel
import com.example.movieappmad23.viewmodels.MoviesViewModelFactory

object InjectorUtils {

    private fun getMoviesRepository(context: Context) : MovieRepository {
        return MovieRepository(MovieDatabase.getDatabase(context).movieDao())
    }
    fun provideMoviesViewModelFactory(context: Context) : MoviesViewModelFactory {

        val repository = getMoviesRepository(context)
        return MoviesViewModelFactory(repository)
    }

    fun provideFavoritesViewModelFactory(context: Context) : FavoritesViewModelFactory {
        val repository = getMoviesRepository(context)
        return FavoritesViewModelFactory(repository)
    }

    fun provideDetailsViewModelFactory(context: Context) : DetailsViewModelFactory {

        val repository = getMoviesRepository(context)
        return DetailsViewModelFactory(repository)
    }

}