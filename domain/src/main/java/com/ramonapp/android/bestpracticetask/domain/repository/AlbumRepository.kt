package com.ramonapp.android.bestpracticetask.domain.repository

import com.ramonapp.android.bestpracticetask.domain.model.AlbumModel
import com.ramonapp.android.bestpracticetask.domain.model.PhotoModel
import com.ramonapp.android.bestpracticetask.domain.model.ResultModel
import com.ramonapp.android.bestpracticetask.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {

    /**
     * Get all Albums.
     *
     * @return List of AlbumModel wrapped in ResultModel
     * or return Error if error happened
     */

    suspend fun getAlbums(): ResultModel<List<AlbumModel>>



    /**
     * Get all Photos.
     *
     * @return List of PhotoModel wrapped in ResultModel
     * or return Error if error happened
     */

    suspend fun getPhotos(): ResultModel<List<PhotoModel>>



    /**
     * Get all Users.
     *
     * @return List of UserModel wrapped in ResultModel
     * or return Error if error happened
     */

    suspend fun getUsers(): ResultModel<List<UserModel>>




    /**
     * Get all Albums stream.
     *
     * @return Flow of AlbumModel list wrapped in ResultModel
     * or return Error if error happened
     */

    fun getAlbumsStream(): Flow<ResultModel<List<AlbumModel>>>



    /**
     * Get all Photos stream.
     *
     * @return Flow of PhotoModel list wrapped in ResultModel
     * or return Error if error happened
     */

    fun getPhotoStream(): Flow<ResultModel<List<PhotoModel>>>



    /**
     * Get all Users stream.
     *
     * @return Flow of UserModel list wrapped in ResultModel
     * or return Error if error happened
     */

    fun getUserStream(): Flow<ResultModel<List<UserModel>>>


}