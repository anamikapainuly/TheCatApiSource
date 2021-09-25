package com.anupras.apl.thecatapisource.network

/**
 * Created by Anamika Painuly on 21/09/21.
 */
class Events<out T>(private val content: T) {
    var hasBeenHandled = false

    fun getContentIfNotHandled(): T? {
        return if (!hasBeenHandled) {
            hasBeenHandled = true
            ApiResult.success(content)
            content
        } else {
            null
        }
    }

    fun peekContent(): T = content
}

