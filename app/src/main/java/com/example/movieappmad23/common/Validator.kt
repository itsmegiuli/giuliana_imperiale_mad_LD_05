package com.example.movieappmad23.common

import com.example.movieappmad23.models.Genre

object Validator {
    fun validateMovieTitle(title: String): ValidationResult {
        return ValidationResult(title.isNotBlank())
    }

    fun validateMovieYear(year: String): ValidationResult {
        return ValidationResult(year.isNotBlank())
    }

    fun validateMovieGenres(genres: List<Genre>): ValidationResult {
        return ValidationResult(genres.isNotEmpty())
    }

    fun validateMovieDirector(director: String): ValidationResult {
        return ValidationResult(director.isNotBlank())
    }

    fun validateMovieActors(actors: String): ValidationResult {
        return ValidationResult(actors.isNotBlank())
    }

    fun validateMovieRating(rating: String): ValidationResult {
        val result = rating.isNotBlank() && rating.toDoubleOrNull() != null && rating.toDouble().let { it in 0.0..10.0 }
        return ValidationResult(result)
    }
}

data class ValidationResult(
    val successful: Boolean
)
