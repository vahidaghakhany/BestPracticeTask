package com.ramonapp.android.bestpracticetask

import com.google.common.truth.Truth.assertThat
import com.ramonapp.android.bestpracticetask.domain.model.AlbumCombinationModel
import com.ramonapp.android.bestpracticetask.domain.model.ResultModel
import com.ramonapp.android.bestpracticetask.domain.usecase.GetAlbumCombinationStreamUseCase
import com.ramonapp.android.bestpracticetask.domain.usecase.GetAlbumCombinationUseCase
import com.ramonapp.android.bestpracticetask.presentation.albums.AlbumsViewModel
import com.ramonapp.android.bestpracticetask.sharedtest.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class AlbumViewModelTest {

    private lateinit var viewModel: AlbumsViewModel
    private lateinit var getAlbumStreamUseCase: GetAlbumCombinationStreamUseCase
    private lateinit var getAlbumUseCase: GetAlbumCombinationUseCase

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        getAlbumUseCase = Mockito.mock(GetAlbumCombinationUseCase::class.java)
        getAlbumStreamUseCase = Mockito.mock(GetAlbumCombinationStreamUseCase::class.java)
        viewModel =
            AlbumsViewModel(getAlbumUseCase, getAlbumStreamUseCase, StandardTestDispatcher())
    }

    @Test
    fun `test ui state must change album list if use case return success with data`() = runTest {

        `when`(getAlbumUseCase()).thenReturn(
            ResultModel.Success(
                listOf(
                    AlbumCombinationModel(0, "", "", "", ""),
                    AlbumCombinationModel(1, "", "", "", "")
                )
            )
        )

        viewModel.getAlbumsData()
        advanceUntilIdle()
        assertThat(viewModel.uiState.first().isLoading).isFalse()
        assertThat(viewModel.uiState.first().albums).hasSize(2)
    }

    @Test
    fun `test ui state must change error message if user case return error`() = runTest {
        `when`(getAlbumUseCase()).thenReturn(ResultModel.Error("error"))

        viewModel.getAlbumsData()
        advanceUntilIdle()
        assertThat(viewModel.uiState.first().isLoading).isFalse()
        assertThat(viewModel.uiState.first().errorMessage).isNotEmpty()
    }

    @Test
    fun `test ui state must change album list if stream use case return success with data`() =
        runTest {

            `when`(getAlbumStreamUseCase()).thenReturn(flow {
                emit(
                    ResultModel.Success(
                        listOf(
                            AlbumCombinationModel(0, "", "", "", ""),
                            AlbumCombinationModel(1, "", "", "", "")
                        )
                    )
                )
            })

            viewModel.getAlbumsStreamData()
            advanceUntilIdle()
            assertThat(viewModel.uiState.first().isLoading).isFalse()
            assertThat(viewModel.uiState.first().albums).hasSize(2)
        }
}