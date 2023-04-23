package com.example.movieappmad23.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappmad23.data.MovieDatabase
import com.example.movieappmad23.repositories.MovieRepository
import com.example.movieappmad23.screens.*
import com.example.movieappmad23.utils.InjectorUtils
import com.example.movieappmad23.viewmodels.MoviesViewModel
import com.example.movieappmad23.viewmodels.MoviesViewModelFactory

@Composable
fun Navigation() {
    val navController = rememberNavController()
/*** following lines are now in InjectorUtils to avoid boilerplate code */
// inside a composable
   // val db = MovieDatabase.getDatabase(LocalContext.current) //inside composable
    //val repository = MovieRepository(movieDao = db.movieDao())
    //val factory = MoviesViewModelFactory(repository)
/** */

    //now we can add a parameter (factory = factory)
    /** new: InjectorUtils*/
    val movieViewModel: MoviesViewModel = viewModel(factory = InjectorUtils.provideMoviesViewModelFactory(LocalContext.current))
    //before: viewModel() no parameter could be added and we need repository as parameter
    //so use viewModelFactory (create class)



    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route){
            HomeScreen(navController = navController, moviesViewModel = movieViewModel)
        }

        composable(Screen.FavoriteScreen.route) {
            FavoriteScreen(navController = navController)
        }
        
        composable(Screen.AddMovieScreen.route) {
            AddMovieScreen(navController = navController, moviesViewModel = movieViewModel)
        }

        // build a route like: root/detail-screen/id=34
        composable(
            Screen.DetailScreen.route,
            arguments = listOf(navArgument(name = DETAIL_ARGUMENT_KEY) {type = NavType.StringType})
        ) { backStackEntry ->    // backstack contains all information from navhost
            DetailScreen(navController = navController,
                moviesViewModel = movieViewModel,
                movieId = backStackEntry.arguments?.getString(DETAIL_ARGUMENT_KEY))   // get the argument from navhost that will be passed
        }
    }
}