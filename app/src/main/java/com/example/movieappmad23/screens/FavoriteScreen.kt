package com.example.movieappmad23.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.utils.InjectorUtils
import com.example.movieappmad23.widgets.MovieRow
import com.example.movieappmad23.widgets.SimpleTopAppBar
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import com.example.movieappmad23.viewmodels.FavoritesViewModel
import com.example.movieappmad23.viewmodels.MoviesViewModel


@Composable
fun FavoriteScreen(navController: NavController, favoritesViewModel: FavoritesViewModel){
    val coroutineScope = rememberCoroutineScope()

  //  val favoritesViewModel: FavoritesViewModel = viewModel(factory = InjectorUtils.provideFavoritesViewModelFactory(LocalContext.current))

    val faveMovieListState by favoritesViewModel.favMovieListState.collectAsState()



    Scaffold(topBar = {
        SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
            Text(text = "My Favorite Movies")
        }
    }){ padding ->
        Column(modifier = Modifier.padding(padding)) {
            LazyColumn {
                    items(faveMovieListState) { movie ->
                        MovieRow(
                            movie = movie,
                            onMovieRowClick = { movieId ->
                                navController.navigate(route = Screen.DetailScreen.withId(movieId))
                            },
                            onFavClick = {
                                coroutineScope.launch {
                                    //update fav movie is a suspend fun, can only be called from coroutine (or another suspend fun)

                                    favoritesViewModel.updateFavMovie(it)
                                }
                            }
                        )
                    }
            }
        }
    }
}


