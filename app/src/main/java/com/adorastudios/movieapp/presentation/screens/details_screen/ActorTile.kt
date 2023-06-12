package com.adorastudios.movieapp.presentation.screens.details_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.adorastudios.movieapp.domain.Actor

@Composable
fun ActorTile(
    modifier: Modifier,
    actor: Actor,
) {
    Column(modifier = modifier) {
        AsyncImage(
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f),
            contentScale = ContentScale.Crop,
            model = actor.imageUrl,
            contentDescription = null,
        )
        Text(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = actor.name,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
        )
    }
}
