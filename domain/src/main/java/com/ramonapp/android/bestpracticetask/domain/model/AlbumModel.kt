package com.ramonapp.android.bestpracticetask.domain.model


sealed class ResultModel<out T> {
    data class Success<T>(val data: T?) : ResultModel<T>()
    data class Error(val errorMessage: String) : ResultModel<Nothing>()
}

data class AlbumCombinationModel(
    val id: Long,
    val photoTitle: String,
    val albumTitle: String,
    val username: String,
    val image: String
)


data class AlbumModel(
    val userId: Long,
    val id: Long,
    val title: String
)

data class PhotoModel(
    val albumId: Long,
    val id: Long,
    val title: String,
    val imageUrl: String
)

data class UserModel(
    val id: Long,
    val username: String
)
