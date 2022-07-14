package com.demo.feed.domain.usecase

import com.demo.feed.domain.data.hackernews.RemoteHackerNewsRepository
import com.demo.shared.domain.model.feed.News
import com.demo.shared.domain.model.feed.TopStories
import com.demo.shared.domain.usecase.FlowUseCase
import com.demo.shared.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class FilterHackerNewsUseCase(
    private val hackerNewsRepository: RemoteHackerNewsRepository,
    private val dispatcher: CoroutineDispatcher
) : FlowUseCase<String, News>(dispatcher) {

    override fun execute(parameters: String): Flow<Result<News>> {
        return flow<Result<News>> {  }
    }

    private suspend fun retrieveTopStories(): Result<TopStories> {
        withContext(dispatcher) {
            hackerNewsRepository.getAskStories()
        }

        return Result.Loading
    }


}