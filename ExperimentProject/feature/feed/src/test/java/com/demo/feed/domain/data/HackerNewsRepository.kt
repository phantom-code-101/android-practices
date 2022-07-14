package com.demo.feed.domain.data

import com.demo.feed.domain.data.hackernews.HackerNewsRepository
import javax.inject.Inject

interface HackerNewsRepository {
    fun getTopStories()
    fun getAskStories()
}

class DefaultHackerNewsRepository @Inject constructor(
    private val hackerNewsDataSource: DefaultHackerNewsDataSource
) : HackerNewsRepository {
    override fun getTopStories() {
        TODO("Not yet implemented")
    }

    override fun getAskStories() {
        TODO("Not yet implemented")
    }

}