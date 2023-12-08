package com.ramonapp.android.bestpracticetask.domain.usecase

import com.ramonapp.android.bestpracticetask.domain.model.AlbumCombinationModel
import com.ramonapp.android.bestpracticetask.domain.model.ResultModel
import com.ramonapp.android.bestpracticetask.domain.model.mapToAlbumCombination
import com.ramonapp.android.bestpracticetask.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

interface GetAlbumCombinationStreamUseCase {
    operator fun invoke(): Flow<ResultModel<List<AlbumCombinationModel>>>
}

class DefaultGetAlbumCombinationStreamUseCase @Inject constructor(
    private val repository: AlbumRepository
) : GetAlbumCombinationStreamUseCase {

    override operator fun invoke(): Flow<ResultModel<List<AlbumCombinationModel>>> = combine(
        repository.getAlbumsStream(),
        repository.getPhotoStream(),
        repository.getUserStream()
    ) { albums, photos, users ->
        val albumList = when (albums) {
            is ResultModel.Success -> albums.data
            is ResultModel.Error -> return@combine albums
        }

        val photoList = when (photos) {
            is ResultModel.Success -> photos.data
            is ResultModel.Error -> return@combine photos
        }

        val userList = when (users) {
            is ResultModel.Success -> users.data
            is ResultModel.Error -> return@combine users
        }

        ResultModel.Success(mapToAlbumCombination(albumList, photoList, userList))
    }

}