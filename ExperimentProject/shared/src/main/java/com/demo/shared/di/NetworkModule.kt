package com.demo.shared.di

import com.demo.shared.BuildConfig
import com.demo.shared.endpoint.HackerNewsEndPoint
import com.demo.shared.endpoint.RandomUserEndPoint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    companion object {
        const val HACKER_NEWS_DOMAIN = "https://hacker-news.firebaseio.com"
        const val RANDOM_USER_DOMAIN = "https://randomuser.me"
    }

    @DefaultOkHttpClient
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor {
                val original = it.request()
                val request = original.newBuilder()
                    .header("os", "android")
                    .method(original.method(), original.body())
                    .build()

                it.proceed(request)
            }

            if (BuildConfig.DEBUG) {
                addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
            }
        }.build()
    }

    @DefaultRetrofitBuilder
    @Provides
    fun provideRetrofitBuilder(
        @DefaultOkHttpClient okHttpClient: OkHttpClient
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @HackerNewsApi
    @Provides
    fun provideHackerNewsEedPoint(
        @DefaultRetrofitBuilder builder: Retrofit.Builder
    ): HackerNewsEndPoint {
        return builder.baseUrl(HACKER_NEWS_DOMAIN)
            .build()
            .create(HackerNewsEndPoint::class.java)
    }

    @RandomUserApi
    @Provides
    fun provideRandomUserEedPoint(
        @DefaultRetrofitBuilder builder: Retrofit.Builder
    ): RandomUserEndPoint {
        return builder.baseUrl(RANDOM_USER_DOMAIN)
            .build()
            .create(RandomUserEndPoint::class.java)
    }

}