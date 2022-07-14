package com.demo.feed.domain.usecase

import com.demo.feed.domain.data.DefaultHackerNewsRepository
import com.demo.shared.domain.model.feed.News
import com.demo.shared.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher

class FilterHackerNewsUseCase(
    private val hackerNewsRepository: DefaultHackerNewsRepository,
    private val dispatcher: CoroutineDispatcher
) : UseCase<String, Result<News>>(dispatcher) {

    override suspend fun execute(parameters: String): Result<News> {
        return Result.failure(Throwable(""))
    }

}