package com.ramonapp.android.bestpracticetask.data

import com.google.common.truth.Truth.assertThat
import com.ramonapp.android.bestpracticetask.data.datasource.AlbumDataSource
import com.ramonapp.android.bestpracticetask.data.repository.DefaultAlbumRepository
import com.ramonapp.android.bestpracticetask.domain.model.ResultModel
import com.ramonapp.android.bestpracticetask.network.model.AlbumDto
import com.ramonapp.android.bestpracticetask.network.model.DataResult
import com.ramonapp.android.bestpracticetask.network.model.ErrorType
import com.ramonapp.android.bestpracticetask.network.model.PhotoDto
import com.ramonapp.android.bestpracticetask.network.model.UserDto
import com.ramonapp.android.bestpracticetask.sharedtest.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`


class AlbumRepositoryTest {

    private lateinit var repository: DefaultAlbumRepository
    private lateinit var dataSource: AlbumDataSource


    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        dataSource = Mockito.mock(AlbumDataSource::class.java)
        repository = DefaultAlbumRepository(dataSource)
    }

    @Test
    fun `test getAlbums must return success when api get success`() = runTest {

        `when`(dataSource.getAlbums()).thenReturn(
            DataResult.Success(listOf(AlbumDto(1, 1, "test")))
        )

        val result = repository.getAlbums()

        advanceUntilIdle()

        assertThat(result).isInstanceOf(ResultModel.Success::class.java)
        assertThat((result as ResultModel.Success).data).hasSize(1)
    }


    @Test
    fun `test getPhotos must return success when api get success`() = runTest {

        `when`(dataSource.getPhotos()).thenReturn(
            DataResult.Success(listOf(PhotoDto(1, 1, "photo test", "url")))
        )

        val result = repository.getPhotos()

        advanceUntilIdle()

        assertThat(result).isInstanceOf(ResultModel.Success::class.java)
        assertThat((result as ResultModel.Success).data).hasSize(1)
    }


    @Test
    fun `test getUsers must return success when api get success`() = runTest {

        `when`(dataSource.getUsers()).thenReturn(
            DataResult.Success(listOf(UserDto(1, "username test")))
        )

        val result = repository.getUsers()

        advanceUntilIdle()

        assertThat(result).isInstanceOf(ResultModel.Success::class.java)
        assertThat((result as ResultModel.Success).data).hasSize(1)
    }


    @Test
    fun `test getPhotos must return error when api get failure`() = runTest {

        `when`(dataSource.getPhotos()).thenReturn(
            DataResult.Failure(ErrorType.ServerError)
        )


        val result = repository.getPhotos()

        advanceUntilIdle()

        assertThat(result).isInstanceOf(ResultModel.Error::class.java)
        assertThat((result as ResultModel.Error).errorMessage).isNotEmpty()
    }


    @Test
    fun `test getAlbumsStream must return success when api get success`() = runTest {

        `when`(dataSource.getAlbumsStream()).thenReturn(
            flow {
                emit(DataResult.Success(listOf(AlbumDto(1, 1, "test"))))
            }
        )


        val result = repository.getAlbumsStream().last()

        assertThat(result).isInstanceOf(ResultModel.Success::class.java)
        assertThat((result as ResultModel.Success).data).hasSize(1)
    }
}