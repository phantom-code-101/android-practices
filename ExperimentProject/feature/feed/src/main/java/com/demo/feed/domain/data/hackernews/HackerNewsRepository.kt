package com.demo.feed.domain.data.hackernews

import javax.inject.Inject

interface HackerNewsRepository {
    fun getTopStories()
    fun getAskStories()
}

class RemoteHackerNewsRepository @Inject constructor(
    private val remoteHackerNewsDataSource: RemoteHackerNewsDataSource,
    private val localHackerNewsDataSource: LocalHackerNewsDataSource
) : HackerNewsRepository {
    override fun getTopStories() {
        TODO("Not yet implemented")
    }

    override fun getAskStories() {
        TODO("Not yet implemented")
    }
}