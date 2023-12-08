package com.ramonapp.android.bestpracticetask.network.model


sealed class DataResult<out T> {
    data class Success<T>(val data: T?) : DataResult<T>()
    data class Failure(val error: ErrorType) : DataResult<Nothing>()
}