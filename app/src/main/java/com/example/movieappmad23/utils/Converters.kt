package com.example.movieappmad23.utils

import androidx.room.TypeConverter
import com.example.movieappmad23.models.Genre

class Converters {

    //List of Genres
    @TypeConverter
    fun genreListToString(value: List<Genre>) : String {
        var newString = ""
        for (x in value) {
            newString += x.name
        }
        return newString
    }

    @TypeConverter
    fun toGenreList(value: String): List<Genre> {
        val listToReturn = mutableListOf<Genre>()
        value.split(",").map{ it.trim()}.forEach {
            listToReturn.add(enumValueOf(it))
        }
        return listToReturn

    }


    //List of Images
   @TypeConverter
    fun stringToList(value: String) : List<String> {
       return value.split(",").map { it.trim() }
   }

    @TypeConverter
    fun listToString(value: List<String>) : String {
        return value.joinToString { ", " }

    }
}
