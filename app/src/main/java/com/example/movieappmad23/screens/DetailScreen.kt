package com.example.movieappmad23.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.viewmodels.DetailsViewModel
import com.example.movieappmad23.widgets.HorizontalScrollableImageView
import com.example.movieappmad23.widgets.MovieRow
import com.example.movieappmad23.widgets.SimpleTopAppBar
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(
    navController: NavController,
    detailsViewModel: DetailsViewModel,
    movieId:String){
    val coroutineScope = rememberCoroutineScope()
    val movieById = detailsViewModel.getMovieById(movieId)

    movieId.let {
        val scaffoldState = rememberScaffoldState() // this contains the `SnackbarHostState`

        Scaffold(scaffoldState = scaffoldState, // attaching `scaffoldState` to the `Scaffold`
            topBar = {
                SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
                    Text(text = movieById.title)
                }
            },
        ) { padding ->
            MainContent(
                Modifier.padding(padding),
                movieById,
                onFavClick = { movie ->
                    coroutineScope.launch {
                        detailsViewModel.updateFavMovie(movie)
                    }
                }
            )
        }
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    movie: Movie,
    onFavClick: (Movie) -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            MovieRow(
                movie = movie,
                onFavClick = { movie ->
                    onFavClick(movie)
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Divider()

            Text(text = "Movie Images", style = MaterialTheme.typography.h5)

            HorizontalScrollableImageView(movie = movie)
        }
    }
}