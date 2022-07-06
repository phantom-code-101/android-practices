package com.demo.landing.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface CryptoCurrencyApi {

    @GET("v1/cryptocurrency/listings/latest")
    fun getListingsLatest(): Call<String>

}