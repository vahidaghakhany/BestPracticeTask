package com.ramonapp.android.bestpracticetask.data.datasource.remote.mapper

import com.ramonapp.android.bestpracticetask.domain.model.AlbumModel
import com.ramonapp.android.bestpracticetask.domain.model.PhotoModel
import com.ramonapp.android.bestpracticetask.domain.model.ResultModel
import com.ramonapp.android.bestpracticetask.domain.model.UserModel
import com.ramonapp.android.bestpracticetask.network.model.AlbumDto
import com.ramonapp.android.bestpracticetask.network.model.DataResult
import com.ramonapp.android.bestpracticetask.network.model.ErrorType
import com.ramonapp.android.bestpracticetask.network.model.PhotoDto
import com.ramonapp.android.bestpracticetask.network.model.UserDto


internal fun List<AlbumDto>?.toAlbumModel(): List<AlbumModel>? {
    return this?.mapNotNull { dto ->
        AlbumModel(
            userId = dto.userId ?: return@mapNotNull null,
            id = dto.id ?: return@mapNotNull null,
            title = dto.title ?: "",
        )
    }
}

internal fun List<PhotoDto>?.toPhotoModel(): List<PhotoModel>? {
    return this?.mapNotNull { dto ->
        PhotoModel(
            albumId = dto.albumId ?: return@mapNotNull null,
            id = dto.id ?: return@mapNotNull null,
            title = dto.title ?: "" ,
            imageUrl = dto.imageUrl ?: ""
        )
    }
}

internal fun List<UserDto>?.toUserModel(): List<UserModel>? {
    return this?.mapNotNull { dto ->
        UserModel(
            id = dto.id ?: return@mapNotNull null,
            username = dto.username ?: ""
        )
    }
}

internal fun mapNetworkError(networkError: DataResult.Failure): ResultModel.Error {
    return ResultModel.Error( when(networkError.error) {
        ErrorType.ClientError -> "Client Error"
        ErrorType.NetworkError -> "Network Connection Error"
        ErrorType.ParseError -> "Server Data Parse Error"
        ErrorType.ServerError -> "Server Error"
        ErrorType.UnExpectedError -> "Unexpected Error"
    })
}