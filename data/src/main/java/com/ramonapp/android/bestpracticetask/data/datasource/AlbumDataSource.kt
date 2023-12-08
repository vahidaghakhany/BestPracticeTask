package com.ramonapp.android.bestpracticetask.data.datasource

import com.ramonapp.android.bestpracticetask.network.model.AlbumDto
import com.ramonapp.android.bestpracticetask.network.model.DataResult
import com.ramonapp.android.bestpracticetask.network.model.PhotoDto
import com.ramonapp.android.bestpracticetask.network.model.UserDto
import kotlinx.coroutines.flow.Flow

interface AlbumDataSource {


    /**
     * Get all Albums.
     *
     * @return List of AlbumDto wrapped in DataResult
     * or return Error if error happened
     */

    suspend fun getAlbums(): DataResult<List<AlbumDto>>


    /**
     * Get all Photos.
     *
     * @return List of PhotoDto wrapped in DataResult
     * or return Error if error happened
     */

    suspend fun getPhotos(): DataResult<List<PhotoDto>>


    /**
     * Get all Users.
     *
     * @return List of UserDto wrapped in DataResult
     * or return Error if error happened
     */

    suspend fun getUsers(): DataResult<List<UserDto>>


    /**
     * Get all Albums stream.
     *
     * @return Flow of AlbumDto list wrapped in ResultModel
     * or return Error if error happened
     */

    fun getAlbumsStream(): Flow<DataResult<List<AlbumDto>>>


    /**
     * Get all Photos stream.
     *
     * @return Flow of PhotoDto list wrapped in ResultModel
     * or return Error if error happened
     */

    fun getPhotosStream(): Flow<DataResult<List<PhotoDto>>>


    /**
     * Get all Users stream.
     *
     * @return Flow of UserDto list wrapped in ResultModel
     * or return Error if error happened
     */

    fun getUsersStream(): Flow<DataResult<List<UserDto>>>


}