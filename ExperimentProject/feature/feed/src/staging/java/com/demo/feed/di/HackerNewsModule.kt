package com.demo.feed.di

import com.demo.feed.domain.data.DefaultHackerNewsDataSource
import com.demo.feed.domain.data.DefaultHackerNewsRepository
import com.demo.feed.domain.usecase.FilterHackerNewsUseCase
import com.demo.shared.di.HackerNewsApi
import com.demo.shared.di.IoDispatcher
import com.demo.shared.endpoint.HackerNewsEndPoint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class HackerNewsModule {

    @Singleton
    @Provides
    fun provideHackerNewsDataSource(
        @HackerNewsApi endPoint: HackerNewsEndPoint
    ): DefaultHackerNewsDataSource {
        return DefaultHackerNewsDataSource(endPoint)
    }

    @Singleton
    @Provides
    fun provideHackerNewsRepository(
        dataSource: DefaultHackerNewsDataSource
    ): DefaultHackerNewsRepository {
        return DefaultHackerNewsRepository(dataSource)
    }

    @Singleton
    @Provides
    fun provideHackerNewsUseCase(
        repository: DefaultHackerNewsRepository,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): FilterHackerNewsUseCase {
        return FilterHackerNewsUseCase(repository, dispatcher)
    }

}