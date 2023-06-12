package com.adorastudios.movieapp.presentation.screens.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.adorastudios.movieapp.domain.MoviePreview

@Composable
fun MoviePreviewTile(
    moviePreview: MoviePreview,
    onCLick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .aspectRatio(0.6f)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable {
                onCLick()
            },
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                model = moviePreview.imageUrl,
                contentDescription = null,
            )

            if (moviePreview.rating >= 0.01) {
                Text(
                    modifier = Modifier
                        .padding(top = 4.dp, start = 4.dp)
                        .clip(MaterialTheme.shapes.small)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(4.dp),
                    maxLines = 1,
                    text = String.format("%.2f", moviePreview.rating),
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            text = moviePreview.title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
    }
}
