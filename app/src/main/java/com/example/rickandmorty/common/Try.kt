package com.example.rickandmorty.common

import android.util.Log
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

sealed class Try<out A> {
    data class Success<out A>(val value: A) : Try<A>()
    data class Failure(val e: Exception) : Try<Nothing>()

    companion object {
        operator fun <A> invoke(body:  () -> A): Try<A> {
            return try {
                Success(body())
            } catch (e: Exception) {
                Log.i("EXCEPTION_EVENT", e.message.toString())
                Failure(e)
            }
        }
      suspend inline fun <A> invokeSuspend(context: CoroutineContext,
                                           crossinline body:suspend  () -> A): Try<A> {
            return try {
                Success(withContext(context){body()})
            } catch (e: Exception) {
                Log.i("EXCEPTION_EVENT", e.message.toString())
                Failure(e)
            }
        }
    }

    inline fun <B> map(f: (A) -> B): Try<B> =
        flatMap { Success(f(it)) }

    inline fun <B> flatMap(f: (A) -> Try<B>): Try<B> =
        when (this) {
            is Failure -> this
            is Success -> f(value)
        }
}