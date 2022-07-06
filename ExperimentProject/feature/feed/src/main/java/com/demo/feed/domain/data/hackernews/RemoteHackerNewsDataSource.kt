package com.demo.feed.domain.data.hackernews

import com.demo.shared.di.HackerNewsApi
import com.demo.shared.domain.model.feed.AskStories
import com.demo.shared.domain.model.feed.News
import com.demo.shared.domain.model.feed.TopStories
import com.demo.shared.endpoint.HackerNewsEndPoint
import javax.inject.Inject

class RemoteHackerNewsDataSource @Inject constructor(
    @HackerNewsApi private val hackerNewsEndPoint: HackerNewsEndPoint
) : HackerNewsDataSource {
    override suspend fun getTopStories(): TopStories? {
        return hackerNewsEndPoint.getTopStoryIds()
    }

    override suspend fun getAskStories(): AskStories? {
        TODO("Not yet implemented")
    }

    override suspend fun getNewsById(id: String): News? {
        TODO("Not yet implemented")
    }
}