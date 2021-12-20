package com.demo.mysorting.model

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val throwable: Throwable) : Result<Nothing>()
    object Loading : Result<Nothing>()
}
