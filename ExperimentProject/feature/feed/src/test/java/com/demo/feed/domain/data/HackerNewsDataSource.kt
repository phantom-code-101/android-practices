package com.demo.feed.domain.data

import com.demo.feed.domain.data.hackernews.HackerNewsDataSource
import com.demo.shared.di.HackerNewsApi
import com.demo.shared.domain.model.feed.AskStories
import com.demo.shared.domain.model.feed.TopStories
import com.demo.shared.endpoint.HackerNewsEndPoint
import javax.inject.Inject

interface HackerNewsDataSource {
    fun getTopStories(): TopStories?
    fun getAskStories(): AskStories?
}

class DefaultHackerNewsDataSource @Inject constructor(
    @HackerNewsApi private val hackerNewsEndPoint: HackerNewsEndPoint
) : HackerNewsDataSource {

    override fun getTopStories(): TopStories? {
        return null
    }

    override fun getAskStories(): AskStories? {
        return null
    }

}