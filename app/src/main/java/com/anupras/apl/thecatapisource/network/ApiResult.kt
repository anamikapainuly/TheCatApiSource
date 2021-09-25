package com.anupras.apl.thecatapisource.network

import android.util.Log

data class ApiResult<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {

        fun <T> success(data: T?): ApiResult<T> {
            return ApiResult(Status.SUCCESS, data, null)
            Log.d("Check-- SUCCESS", data.toString())
        }

        fun <T> error(msg: String, data: T?): ApiResult<T> {
            return ApiResult(Status.ERROR, data, msg)
            Log.d("Check-- ERROR", msg)
        }

        fun <T> loading(data: T?): ApiResult<T> {
            return ApiResult(Status.LOADING, data, null)
        }

    }
}
