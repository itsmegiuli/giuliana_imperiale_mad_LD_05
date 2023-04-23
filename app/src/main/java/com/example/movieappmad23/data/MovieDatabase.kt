package com.example.movieappmad23.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.utils.Converters

@Database(
    entities = [Movie::class], //here come ALL entities that this database will manage
    version = 10, //important for migrations
    exportSchema = true //true=schema will export
)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() { //inherits from RoomDatabase
    abstract fun movieDao(): MovieDao
    // if more entities --> more DAOs here
    companion object {
        @Volatile //= never cache the value of Instance
        private var Instance: MovieDatabase?=null

        fun getDatabase (context: Context): MovieDatabase {
        return Instance?: synchronized( this ) {
                Room.databaseBuilder(context, MovieDatabase::class.java, "movie_db")
                    .fallbackToDestructiveMigration() // if schema changes wipe the whole schema --- other migration strategy would go here
                    .build() // BuilderPattern --> creates an instance of the DB
                    .also {
                        Instance = it // override Instance with newly created DB
                    }
            }
        }

    }
}