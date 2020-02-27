package com.appcent.flickr.data.remote.service.error

import android.os.Parcelable
import com.google.gson.JsonParseException
import com.orhanobut.logger.Logger
import kotlinx.android.parcel.Parcelize
import org.json.JSONException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

@Parcelize
class ApiError(
    var status: ErrorStatus = ErrorStatus.UNKNOWN_ERROR,
    var message: String = "Request unsuccessful"
) : Parcelable {

    companion object {
        fun handleException(apiCode: Int?, message: String?): ApiError {
            val error = ApiError()
            error.message = message ?: ""
            error.status = when (apiCode) {
                ErrorStatus.API_ERROR.code() -> ErrorStatus.API_ERROR
                ErrorStatus.SERVICE_UNAVAIBLE.code() -> ErrorStatus.SERVICE_UNAVAIBLE
                ErrorStatus.WRITE_OPERATION.code() -> ErrorStatus.WRITE_OPERATION
                ErrorStatus.FORMAT_ERROR.code() -> ErrorStatus.FORMAT_ERROR
                ErrorStatus.METHOD_ERROR.code() -> ErrorStatus.METHOD_ERROR
                ErrorStatus.INVALID_SOAP_ERROR.code() -> ErrorStatus.INVALID_SOAP_ERROR
                ErrorStatus.INVALID_XML_RPC_ERROR.code() -> ErrorStatus.INVALID_XML_RPC_ERROR
                ErrorStatus.BAD_URL_ERROR.code() -> ErrorStatus.BAD_URL_ERROR
                else -> ErrorStatus.UNKNOWN_ERROR
            }

            return error
        }

        fun handleException(e: Throwable): ApiError {
            val error = ApiError()
            if (e is SocketTimeoutException) {
                Logger.e("TAG", "Timeout: " + e.message)
                error.message = "timeout"
                error.status = ErrorStatus.NETWORK_ERROR
            } else if (e is ConnectException) {
                Logger.e("TAG", "No Connect: " + e.message)
                error.message = "No Connect"
                error.status = ErrorStatus.NETWORK_ERROR
            } else if (e is JsonParseException
                || e is JSONException
                || e is ParseException
            ) {
                Logger.e("TAG", "Parse Exception: " + e.message)
                error.message = "Parse Exception"
                error.status = ErrorStatus.SERVER_ERROR
            } else if (e is ApiException) {//sunucu tarafındaki hata için
                error.message = e.message.toString()
                error.status = ErrorStatus.SERVER_ERROR
            } else if (e is UnknownHostException) {
                Logger.e("TAG", " UnknownHostException: " + e.message)
                error.message = " UnknownHostException"
                error.status = ErrorStatus.NETWORK_ERROR
            } else if (e is IllegalArgumentException) {
                error.message = "IllegalArgumentException"
                error.status = ErrorStatus.SERVER_ERROR
            } else {
                try {
                    Logger.e("TAG", "Message: " + e.message)
                } catch (e1: Exception) {
                    Logger.e("TAG", "Unknown Exception")
                }

                error.message = "Unknown Exception"
                error.status = ErrorStatus.UNKNOWN_ERROR
            }
            return error
        }
    }
}