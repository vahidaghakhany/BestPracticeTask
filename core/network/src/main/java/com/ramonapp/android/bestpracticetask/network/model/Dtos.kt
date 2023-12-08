package com.ramonapp.android.bestpracticetask.network.model

import com.google.gson.annotations.SerializedName

data class AlbumDto (
    @SerializedName("userId") val userId: Long?,
    @SerializedName("id") val id: Long?,
    @SerializedName("title") val title: String?
)

data class PhotoDto (
    @SerializedName("albumId") val albumId: Long?,
    @SerializedName("id") val id: Long?,
    @SerializedName("title") val title: String?,
    @SerializedName("thumbnailUrl") val imageUrl: String?
)

data class UserDto (
    @SerializedName("id") val id: Long?,
    @SerializedName("username") val username: String?
)