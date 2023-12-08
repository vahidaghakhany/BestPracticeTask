package com.ramonapp.android.bestpracticetask.presentation.albums

import com.ramonapp.android.bestpracticetask.domain.model.AlbumCombinationModel

internal data class AlbumsUiState (
    val albums: List<AlbumCombinationModel> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)