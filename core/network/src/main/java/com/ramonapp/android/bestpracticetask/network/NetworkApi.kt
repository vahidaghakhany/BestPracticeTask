package com.ramonapp.android.bestpracticetask.network

import com.ramonapp.android.bestpracticetask.network.model.AlbumDto
import com.ramonapp.android.bestpracticetask.network.model.DataResult
import com.ramonapp.android.bestpracticetask.network.model.PhotoDto
import com.ramonapp.android.bestpracticetask.network.model.UserDto
import retrofit2.http.GET

interface NetworkApi {


    /**
     * Retrieves the list of albums.
     */
    @GET("albums")
    suspend fun getAlbums(): DataResult<List<AlbumDto>>




    /**
     * Retrieves the list of photos.
     */
    @GET("photos")
    suspend fun getPhotos(): DataResult<List<PhotoDto>>




    /**
     * Retrieves the list of users.
     */
    @GET("users")
    suspend fun getUsers(): DataResult<List<UserDto>>

}