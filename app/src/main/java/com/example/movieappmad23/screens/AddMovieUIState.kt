package com.example.movieappmad23.screens

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.example.movieappmad23.common.Validator
import com.example.movieappmad23.models.Genre
import com.example.movieappmad23.models.ListItemSelectable
import com.example.movieappmad23.models.Movie

data class AddMovieUiState(
    val title: String = "",
    val year: String = "",
    val genre: List<Genre> = listOf(),
    val director: String = "",
    val actors: String = "",
    val plot: String = "",
    val images: List<String> = listOf(),
    val rating: String = "",
    var selectableGenreItems: SnapshotStateList<ListItemSelectable> = Genre.values().toList().map { genre ->
        ListItemSelectable(title = genre.toString())
    }.toMutableStateList(),
    var titleErr: Boolean = false,
    val yearErr: Boolean = false,
    val directorErr: Boolean = false,
    val actorsErr: Boolean = false,
    val ratingErr: Boolean = false,
    val genreErr: Boolean = false,
    val actionEnabled: Boolean = false,
)

fun AddMovieUiState.selectGenre(item: ListItemSelectable): List<Genre> {
    selectableGenreItems.find { it.title == item.title }?.let { genre ->
        genre.isSelected = !genre.isSelected
    }

    return selectableGenreItems.filter { item -> item.isSelected }.map { listItemSelectable ->
        Genre.valueOf(listItemSelectable.title)
    }
}

fun AddMovieUiState.toMovie(): Movie = Movie(
    title = title,
    year = year,
    genre = genre,
    director = director,
    actors = actors,
    plot = plot,
    images = images,
    rating = rating.toDoubleOrNull() ?: 0.0
)

fun AddMovieUiState.toMovieUiState(actionEnabled: Boolean): AddMovieUiState = AddMovieUiState(
    title = title,
    year = year,
    genre = genre,
    director = director,
    actors = actors,
    plot = plot,
    images = images,
    rating = rating,
    actionEnabled = actionEnabled
)

fun AddMovieUiState.hasError() : Boolean {
    val titleResult = Validator.validateMovieTitle(title)
    val yearResult = Validator.validateMovieYear(year)
    val genreResult = Validator.validateMovieGenres(genre)
    val directorResult = Validator.validateMovieDirector(director)
    val actorsResult = Validator.validateMovieActors(actors)
    val ratingResult = Validator.validateMovieRating(rating)

    return listOf(
        titleResult,
        yearResult,
        genreResult,
        directorResult,
        actorsResult,
        ratingResult
    ).any {
        !it.successful
    }
}

