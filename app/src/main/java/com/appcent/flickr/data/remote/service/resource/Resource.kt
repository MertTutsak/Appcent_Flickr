package com.appcent.flickr.data.remote.service.resource

import androidx.annotation.NonNull
import com.appcent.flickr.data.remote.service.error.ApiError

class Resource<T> constructor(val status: Status, val data: T?, val error: ApiError? = null) {

    companion object {

        fun <T> success(@NonNull data: T): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data
            )
        }

        fun <T> error(throwable: Throwable): Resource<T> {
            return Resource(
                status = Status.ERROR,
                data = null,
                error = ApiError.handleException(throwable)
            )
        }

        fun <T> apiError(error: ApiError): Resource<T> {
            return Resource(
                status = Status.ERROR,
                data = null,
                error = error
            )

        }

        fun <T> loading(): Resource<T> =
            Resource(
                Status.LOADING,
                null
            )
    }
}