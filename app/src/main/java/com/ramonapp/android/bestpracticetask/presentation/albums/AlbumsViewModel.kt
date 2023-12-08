package com.ramonapp.android.bestpracticetask.presentation.albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramonapp.android.bestpracticetask.domain.di.DispatcherIO
import com.ramonapp.android.bestpracticetask.domain.model.ResultModel
import com.ramonapp.android.bestpracticetask.domain.usecase.GetAlbumCombinationStreamUseCase
import com.ramonapp.android.bestpracticetask.domain.usecase.GetAlbumCombinationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AlbumsViewModel @Inject constructor(
    private val getAlbumUseCase: GetAlbumCombinationUseCase,
    private val getAlbumStreamUseCase: GetAlbumCombinationStreamUseCase,
    @DispatcherIO private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(AlbumsUiState())
    val uiState: StateFlow<AlbumsUiState> = _uiState

    fun getAlbumsData() {
        viewModelScope.launch(dispatcher) {
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = true
                )
            }
            when (val result = getAlbumUseCase()) {
                is ResultModel.Success -> {
                    _uiState.update { currentState ->
                        currentState.copy(
                            albums = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                }

                is ResultModel.Error -> {
                    _uiState.update { currentState ->
                        currentState.copy(
                            errorMessage = result.errorMessage,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    fun getAlbumsStreamData() {
        viewModelScope.launch(dispatcher) {
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = true
                )
            }
            getAlbumStreamUseCase()
                .catch {
                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            errorMessage = "General Error"
                        )
                    }
                }
                .collectLatest { result ->
                    when (result) {
                        is ResultModel.Success -> {
                            _uiState.update { currentState ->
                                currentState.copy(
                                    albums = result.data ?: emptyList(),
                                    isLoading = false
                                )
                            }
                        }

                        is ResultModel.Error -> {
                            _uiState.update { currentState ->
                                currentState.copy(
                                    errorMessage = result.errorMessage,
                                    isLoading = false
                                )
                            }
                        }
                    }
                }
        }
    }

}