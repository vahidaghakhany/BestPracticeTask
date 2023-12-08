package com.ramonapp.android.bestpracticetask.domain.usecase

import com.ramonapp.android.bestpracticetask.domain.di.DispatcherIO
import com.ramonapp.android.bestpracticetask.domain.model.AlbumCombinationModel
import com.ramonapp.android.bestpracticetask.domain.model.ResultModel
import com.ramonapp.android.bestpracticetask.domain.model.mapToAlbumCombination
import com.ramonapp.android.bestpracticetask.domain.repository.AlbumRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetAlbumCombinationUseCase {
    suspend operator fun invoke(): ResultModel<List<AlbumCombinationModel>>
}

class DefaultGetAlbumCombinationUseCase @Inject constructor(
    private val repository: AlbumRepository,
    @DispatcherIO private val dispatcher: CoroutineDispatcher
) : GetAlbumCombinationUseCase {

    override suspend operator fun invoke(): ResultModel<List<AlbumCombinationModel>> {
        return withContext(dispatcher) {
            val defAlbum = async { repository.getAlbums() }
            val defPhoto = async { repository.getPhotos() }
            val defUser = async { repository.getUsers() }

            val albums = when (val result = defAlbum.await()) {
                is ResultModel.Success -> result.data
                is ResultModel.Error -> return@withContext result
            }

            val photos = when (val result = defPhoto.await()) {
                is ResultModel.Success -> result.data
                is ResultModel.Error -> return@withContext result
            }

            val users = when (val result = defUser.await()) {
                is ResultModel.Success -> result.data
                is ResultModel.Error -> return@withContext result
            }

            ResultModel.Success(mapToAlbumCombination(albums, photos, users))
        }

    }

}