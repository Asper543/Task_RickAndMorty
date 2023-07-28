package com.example.rickandmorty.common

import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend inline fun <T> Call<T>.doWithTry(): Try<T> =
    Try.invokeSuspend(Dispatchers.IO) {
        suspendCoroutine { result ->
            this.enqueue(
                object : Callback<T> {
                    override fun onResponse(call: Call<T>?, response: Response<T>?) {
                        response?.let {
                            result.resumeWith(Result.success(it.body()))
                        } ?: result.resumeWithException(NullPointerException())
                    }
                    override fun onFailure(call: Call<T>?, t: Throwable?) {
                        t?.let { result.resumeWithException(it) } ?: result.resumeWithException(
                            NullPointerException()
                        )
                    }
                }
            )
        }
    }