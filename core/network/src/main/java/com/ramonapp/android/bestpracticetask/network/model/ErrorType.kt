package com.ramonapp.android.bestpracticetask.network.model

sealed class ErrorType {
    data object NetworkError: ErrorType()
    data object ClientError: ErrorType()
    data object ServerError: ErrorType()
    data object ParseError: ErrorType()
    data object UnExpectedError: ErrorType()
}
