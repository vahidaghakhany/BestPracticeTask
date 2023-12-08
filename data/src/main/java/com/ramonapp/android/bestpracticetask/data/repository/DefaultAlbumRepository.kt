package com.ramonapp.android.bestpracticetask.data.repository

import com.ramonapp.android.bestpracticetask.data.datasource.AlbumDataSource
import com.ramonapp.android.bestpracticetask.data.datasource.remote.mapper.mapNetworkError
import com.ramonapp.android.bestpracticetask.data.datasource.remote.mapper.toAlbumModel
import com.ramonapp.android.bestpracticetask.data.datasource.remote.mapper.toPhotoModel
import com.ramonapp.android.bestpracticetask.data.datasource.remote.mapper.toUserModel
import com.ramonapp.android.bestpracticetask.domain.model.AlbumModel
import com.ramonapp.android.bestpracticetask.domain.model.PhotoModel
import com.ramonapp.android.bestpracticetask.domain.model.ResultModel
import com.ramonapp.android.bestpracticetask.domain.model.UserModel
import com.ramonapp.android.bestpracticetask.domain.repository.AlbumRepository
import com.ramonapp.android.bestpracticetask.network.model.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultAlbumRepository @Inject constructor(
    private val dataSource: AlbumDataSource
) : AlbumRepository {

    override suspend fun getAlbums(): ResultModel<List<AlbumModel>> {
        return when (val result = dataSource.getAlbums()) {
            is DataResult.Success -> ResultModel.Success(result.data.toAlbumModel())
            is DataResult.Failure -> mapNetworkError(result)
        }
    }

    override suspend fun getPhotos(): ResultModel<List<PhotoModel>> {
        return when (val result = dataSource.getPhotos()) {
            is DataResult.Success -> ResultModel.Success(result.data.toPhotoModel())
            is DataResult.Failure -> mapNetworkError(result)
        }
    }

    override suspend fun getUsers(): ResultModel<List<UserModel>> {
        return when (val result = dataSource.getUsers()) {
            is DataResult.Success -> ResultModel.Success(result.data.toUserModel())
            is DataResult.Failure -> mapNetworkError(result)
        }
    }

    override fun getAlbumsStream(): Flow<ResultModel<List<AlbumModel>>> {
        return dataSource.getAlbumsStream().map { result ->
            when (result) {
                is DataResult.Success -> ResultModel.Success(result.data.toAlbumModel())
                is DataResult.Failure -> mapNetworkError(result)
            }
        }
    }

    override fun getPhotoStream(): Flow<ResultModel<List<PhotoModel>>> {
        return dataSource.getPhotosStream().map { result ->
            when (result) {
                is DataResult.Success -> ResultModel.Success(result.data.toPhotoModel())
                is DataResult.Failure -> mapNetworkError(result)
            }
        }
    }

    override fun getUserStream(): Flow<ResultModel<List<UserModel>>> {
        return dataSource.getUsersStream().map { result ->
            when (result) {
                is DataResult.Success -> ResultModel.Success(result.data.toUserModel())
                is DataResult.Failure -> mapNetworkError(result)
            }
        }
    }
}