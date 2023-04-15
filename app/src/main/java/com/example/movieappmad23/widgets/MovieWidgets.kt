package com.example.movieappmad23.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.movieappmad23.R
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.models.getMovies
import com.example.movieappmad23.ui.theme.Shapes

@Preview
@Composable
fun MovieRow(
    movie: Movie = getMovies()[0],
    modifier: Modifier = Modifier,
    onMovieRowClick: (String) -> Unit = {},
    onFavClick: (Movie) -> Unit = {}
) {
    Card(modifier = modifier
        .clickable {
            onMovieRowClick(movie.id)
        }
        .fillMaxWidth()
        .padding(5.dp),
        shape = Shapes.large,
        elevation = 10.dp
    ) {
        Column {
            Box(modifier = Modifier
                .height(150.dp)
                .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                if(movie.images.isNotEmpty()){
                    MovieImage(imageUrl = movie.images[0])
                } else {
                    Image(painter = painterResource(id = R.drawable.no_image_placeholder), contentDescription = "Prev Image")
                }
                FavoriteIcon(movie, onFavClick)
            }

            MovieDetails(modifier = Modifier.padding(12.dp), movie = movie)
        }
    }
}

@Composable
fun MovieImage(imageUrl: String) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentScale = ContentScale.Crop,
        contentDescription = stringResource(id = R.string.movie_poster),
        loading = {
            CircularProgressIndicator()
        }
    )


}

@Composable
fun FavoriteIcon(movie: Movie, onFavClick: (Movie) -> Unit) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp),
        contentAlignment = Alignment.TopEnd
    ){
        Icon(
            tint = MaterialTheme.colors.secondary,
            imageVector =
            if(movie.isFavorite) {
                Icons.Default.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = "Add to favorites",
            modifier = Modifier.clickable {
                onFavClick(movie)
            }
        )
    }
}


@Composable
fun MovieDetails(modifier: Modifier = Modifier, movie: Movie) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            movie.title,
            modifier = Modifier.weight(6f),
            style = MaterialTheme.typography.h6
        )

        IconButton(
            modifier = Modifier.weight(1f),
            onClick = { expanded = !expanded }) {
            Icon(imageVector =
            if (expanded) Icons.Filled.KeyboardArrowDown
            else Icons.Filled.KeyboardArrowUp,
                contentDescription = "expand",
                modifier = Modifier
                    .size(25.dp),
                tint = Color.DarkGray
            )
        }
    }

    AnimatedVisibility(
        visible = expanded,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column (modifier = modifier) {
            Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.caption)
            Text(text = "Released: ${movie.year}", style = MaterialTheme.typography.caption)
            Text(buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp)) {
                    append("Genres: ")
                }

                for ( genre in movie.genre){
                    append("$genre ")
                }
                },
                style = MaterialTheme.typography.caption
            )
            Text(text = "Actors: ${movie.actors}", style = MaterialTheme.typography.caption)
            Text(text = "Rating: ${movie.rating}", style = MaterialTheme.typography.caption)

            Divider(modifier = Modifier.padding(3.dp))

            Text(buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp)) {
                    append("Plot: ")
                }
                withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp, fontWeight = FontWeight.Light)){
                    append(movie.plot)
                }
            })
        }
    }
}

@Composable
fun HorizontalScrollableImageView(movie: Movie) {
    LazyRow {
        items(movie.images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp),
                elevation = 4.dp
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Movie poster",
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}