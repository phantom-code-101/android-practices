package com.demo.core.di

import android.util.Log
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.internal.http2.ConnectionShutdownException
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

object NetworkInjection {

    private val TAG = "Network"

    // We can utilize the Moshi as alternative for replace the
    inline fun <reified T> provideRetrofit(apiUrl: String, clazz: Class<T>): T {
        val builder = Retrofit.Builder()
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(apiUrl)
        return builder.build().create(clazz::class.java) as T
    }

    fun provideOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
        val builder = OkHttpClient().newBuilder().apply {
            callTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            connectTimeout(60, TimeUnit.SECONDS)
            addInterceptor(provideInterceptor())
            addInterceptor(logger)
        }

        return builder.build()
    }

    private fun provideInterceptor(): Interceptor {
        return Interceptor { chain ->

            val request = chain.request()
            val response = chain.proceed(request)
            val responseStr = response.body?.string()
            var error = "Unexpected Error!"

            try {
                response.newBuilder()
                    .addHeader("X-CMC_PRO_API_KEY", "")
                    .body((responseStr ?: "{}").toResponseBody(response.body?.contentType()))
                    .build()
            } catch (e: Exception) {
                when (e) {
                    is SocketTimeoutException -> {
                        error = "Timeout : Please check your device network!"
                    }
                    is UnknownHostException -> {
                        error = "Host : The domain isn't recognize the host."
                    }
                    is ConnectionShutdownException -> {
                        error = "Server Shutdown: The server doesn't work. It should be closed."
                    }
                    is IOException -> {
                        error = "Server is unreachable. please try later!"
                    }
                }
            }

            Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(999)
                .body("{'status': false, 'error': ${error}}".toResponseBody("application/json".toMediaType()))
                .build()
        }
    }

}