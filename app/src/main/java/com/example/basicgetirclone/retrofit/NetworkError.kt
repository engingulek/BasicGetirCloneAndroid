package com.example.basicgetirclone.retrofit

sealed class NetworkError : Throwable() {
    object InvalidResponse : NetworkError()
    object ValueError : NetworkError()
    object BadRequest : NetworkError()
    object NotFound : NetworkError()
    object UnexpectedError : NetworkError()

    override fun toString(): String {
        return when (this) {
            is InvalidResponse -> "Invalid response from the server."
            is ValueError -> "Value error occurred."
            is BadRequest -> "Bad request error."
            is NotFound -> "Resource not found."
            is UnexpectedError -> "An unexpected error occurred."
        }
    }
}
