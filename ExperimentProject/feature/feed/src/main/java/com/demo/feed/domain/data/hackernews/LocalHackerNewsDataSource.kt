package com.demo.feed.domain.data.hackernews

import com.demo.shared.domain.model.feed.AskStories
import com.demo.shared.domain.model.feed.News
import com.demo.shared.domain.model.feed.TopStories

class LocalHackerNewsDataSource(
    /* Database */
): HackerNewsDataSource {
    override suspend fun getTopStories(): TopStories? {
        TODO("Not yet implemented")
    }

    override suspend fun getAskStories(): AskStories? {
        TODO("Not yet implemented")
    }

    override suspend fun getNewsById(id: String): News? {
        TODO("Not yet implemented")
    }
}