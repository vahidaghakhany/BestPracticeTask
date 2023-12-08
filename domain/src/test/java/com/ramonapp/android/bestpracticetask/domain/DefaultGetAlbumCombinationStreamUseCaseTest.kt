package com.ramonapp.android.bestpracticetask.domain

import com.google.common.truth.Truth.assertThat
import com.ramonapp.android.bestpracticetask.domain.model.AlbumModel
import com.ramonapp.android.bestpracticetask.domain.model.PhotoModel
import com.ramonapp.android.bestpracticetask.domain.model.ResultModel
import com.ramonapp.android.bestpracticetask.domain.model.UserModel
import com.ramonapp.android.bestpracticetask.domain.repository.AlbumRepository
import com.ramonapp.android.bestpracticetask.domain.usecase.DefaultGetAlbumCombinationStreamUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class DefaultGetAlbumCombinationStreamUseCaseTest {

    private lateinit var repository: AlbumRepository
    private lateinit var useCase: DefaultGetAlbumCombinationStreamUseCase

    @Before
    fun setup() {
        repository = Mockito.mock(AlbumRepository::class.java)
        useCase = DefaultGetAlbumCombinationStreamUseCase(repository)
    }

    @Test
    fun `test get albums stream`() = runTest {
        `when`(repository.getAlbumsStream()).thenReturn(
            flow { emit(ResultModel.Success(listOf(
                AlbumModel(1, 1, "a"),
                AlbumModel(1, 1, "b"),
                AlbumModel(1, 1, ""),
                AlbumModel(1, 1, "c"),
            ))) }
        )

        `when`(repository.getPhotoStream()).thenReturn(
            flow { emit(ResultModel.Success(listOf(
                PhotoModel(1,1,"",""),
            ))) }
        )

        `when`(repository.getUserStream()).thenReturn(
            flow { emit(ResultModel.Success(listOf(
                UserModel(1,"aaa"),
            ))) }
        )

        val result = useCase.invoke()
        assertThat((result.first() as ResultModel.Success).data).hasSize(4)
    }

}