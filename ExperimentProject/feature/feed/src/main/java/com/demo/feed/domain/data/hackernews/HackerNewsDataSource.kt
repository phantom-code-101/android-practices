package com.demo.feed.domain.data.hackernews

import com.demo.shared.domain.model.feed.AskStories
import com.demo.shared.domain.model.feed.News
import com.demo.shared.domain.model.feed.TopStories

interface HackerNewsDataSource {
    suspend fun getTopStories(): TopStories?
    suspend fun getAskStories(): AskStories?
    suspend fun getNewsById(id: String): News?
}