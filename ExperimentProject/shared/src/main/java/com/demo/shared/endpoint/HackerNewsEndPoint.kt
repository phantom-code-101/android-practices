package com.demo.shared.endpoint

import com.demo.shared.domain.model.feed.News
import com.demo.shared.domain.model.feed.TopStories
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HackerNewsEndPoint {

    @GET("v0/topstories.json?print=pretty")
    suspend fun getTopStoryIds() : TopStories?

    @GET("v0/item/{id}.json?print=pretty")
    suspend fun getNews(@Path("id") id: String) : News

}