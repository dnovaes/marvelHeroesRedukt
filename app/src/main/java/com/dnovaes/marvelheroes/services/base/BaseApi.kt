package com.dnovaes.marvelheroes.services.base

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import timber.log.Timber

abstract class BaseApi {

    abstract val serviceURL: String

    abstract val retrofit: Retrofit

    protected fun <T> handleResponse(callback: (chart: T?, error: String?) -> Unit): Callback<T> {
        return object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                Timber.v("logd handle response failed: ${t.localizedMessage}, ${t.message}, ${t.cause}, ${t.hashCode()}")
                callback.invoke(null, t.message)
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    callback.invoke(response.body(), null)
                } else {
                    callback.invoke(null,
                        response.errorBody()?.string() ?: response.code().toString())
                }
            }
        }
    }
}