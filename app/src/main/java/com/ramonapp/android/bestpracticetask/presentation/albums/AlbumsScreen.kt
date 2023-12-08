package com.ramonapp.android.bestpracticetask.presentation.albums

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.ramonapp.android.bestpracticetask.R
import com.ramonapp.android.bestpracticetask.domain.model.AlbumCombinationModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
internal fun AlbumsScreen(
    viewModel: AlbumsViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val context = LocalContext.current
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                navigationIcon = {
                    IconButton(onClick = { (context as? Activity)?.finish() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = ""
                        )
                    }
                }
            )
        }
    ){
        viewModel.getAlbumsStreamData()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        AlbumsContent(uiState.albums, uiState.isLoading)
        if (uiState.errorMessage.isNotBlank()) {
            LaunchedEffect(scaffoldState) {
                scaffoldState.snackbarHostState.showSnackbar(uiState.errorMessage)
            }
        }
    }

}

@Composable
private fun AlbumsContent(
    albums: List<AlbumCombinationModel>,
    isLoading: Boolean
) {
    LazyColumn {
        items(albums) { album ->
            AlbumItem(album = album)
        }
    }
    if (isLoading) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun AlbumItem(
    album: AlbumCombinationModel
) {
    Row {
        AsyncImage(
            model = album.image,
            contentDescription = "",
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .padding(8.dp),
            placeholder = painterResource(id = R.drawable.ic_launcher_background)
        )

        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = album.photoTitle, modifier = Modifier
                    .padding(top = 16.dp)
            )
            Text(
                text = album.albumTitle, modifier = Modifier
                    .padding(top = 8.dp)
            )
            Text(
                text = album.username, modifier = Modifier
                    .padding(top = 8.dp)
            )
        }
    }
}

@Preview
@Composable
private fun ScreenPreview() {
    AlbumsContent(
        albums = listOf(
            AlbumCombinationModel(
                1,
                "Title Photo",
                "Title Album",
                "Username",
                ""
            ),
            AlbumCombinationModel(
                2,
                "Title Photo",
                "Title Album",
                "Username",
                ""
            )
        ),
        isLoading = false
    )
}