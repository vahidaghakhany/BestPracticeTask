package com.ramonapp.android.bestpracticetask.domain

import com.google.common.truth.Truth.assertThat
import com.ramonapp.android.bestpracticetask.domain.model.AlbumModel
import com.ramonapp.android.bestpracticetask.domain.model.PhotoModel
import com.ramonapp.android.bestpracticetask.domain.model.ResultModel
import com.ramonapp.android.bestpracticetask.domain.model.UserModel
import com.ramonapp.android.bestpracticetask.domain.repository.AlbumRepository
import com.ramonapp.android.bestpracticetask.domain.usecase.DefaultGetAlbumCombinationUseCase
import com.ramonapp.android.bestpracticetask.sharedtest.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class DefaultGetAlbumCombinationUseCaseTest {

    private lateinit var repository: AlbumRepository
    private lateinit var useCase: DefaultGetAlbumCombinationUseCase

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        repository = Mockito.mock(AlbumRepository::class.java)
        useCase = DefaultGetAlbumCombinationUseCase(repository, StandardTestDispatcher())
    }

    @Test
    fun `test get albums`() = runTest {
        `when`(repository.getAlbums()).thenReturn(
            ResultModel.Success(listOf(
                AlbumModel(1, 1, "a"),
                AlbumModel(1, 1, "b"),
                AlbumModel(1, 1, ""),
                AlbumModel(1, 1, "c"),
            ))
        )

        `when`(repository.getPhotos()).thenReturn(
            ResultModel.Success(listOf(
                PhotoModel(1,1,"",""),
            ))
        )

        `when`(repository.getUsers()).thenReturn(
            ResultModel.Success(listOf(
                UserModel(1,"aaa"),
            ))
        )

        val result = useCase.invoke()
        assertThat((result as ResultModel.Success).data).hasSize(4)
    }

}