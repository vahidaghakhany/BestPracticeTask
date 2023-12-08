package com.ramonapp.android.bestpracticetask.domain.model


internal fun mapToAlbumCombination(
    albums: List<AlbumModel>?,
    photos: List<PhotoModel>?,
    users: List<UserModel>?
): List<AlbumCombinationModel>? {
    return albums?.map { album ->
        val photo = photos?.firstOrNull { photo ->
            photo.albumId == album.id
        }
        val user = users?.firstOrNull { user ->
            user.id == album.userId
        }
        AlbumCombinationModel(
            id = album.id,
            photoTitle = photo?.title ?: "",
            albumTitle = album.title,
            username = user?.username ?: "",
            image = photo?.imageUrl ?: ""
        )
    }
}