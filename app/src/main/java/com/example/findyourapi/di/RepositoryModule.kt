package com.example.findyourapi.di

import com.example.findyourapi.service.API.PublicApiService
import com.example.findyourapi.service.repository.PublicApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePublicApiRepository(publicApiService: PublicApiService):PublicApiRepository = PublicApiRepository(publicApiService)
}