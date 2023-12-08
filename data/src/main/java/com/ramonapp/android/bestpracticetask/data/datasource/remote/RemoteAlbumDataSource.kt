package com.ramonapp.android.bestpracticetask.data.datasource.remote

import com.ramonapp.android.bestpracticetask.data.datasource.AlbumDataSource
import com.ramonapp.android.bestpracticetask.network.NetworkApi
import com.ramonapp.android.bestpracticetask.network.model.AlbumDto
import com.ramonapp.android.bestpracticetask.network.model.DataResult
import com.ramonapp.android.bestpracticetask.network.model.PhotoDto
import com.ramonapp.android.bestpracticetask.network.model.UserDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteAlbumDataSource @Inject constructor(
    private val networkApi: NetworkApi
) : AlbumDataSource {

    override suspend fun getAlbums(): DataResult<List<AlbumDto>> {
        return networkApi.getAlbums()
    }

    override suspend fun getPhotos(): DataResult<List<PhotoDto>> {
        return networkApi.getPhotos()
    }

    override suspend fun getUsers(): DataResult<List<UserDto>> {
        return networkApi.getUsers()
    }

    override fun getAlbumsStream(): Flow<DataResult<List<AlbumDto>>> = flow {
        emit(networkApi.getAlbums())
    }

    override fun getPhotosStream(): Flow<DataResult<List<PhotoDto>>> = flow {
        emit(networkApi.getPhotos())
    }

    override fun getUsersStream(): Flow<DataResult<List<UserDto>>> = flow {
        emit(networkApi.getUsers())
    }
}