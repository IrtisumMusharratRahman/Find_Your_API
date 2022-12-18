package com.example.findyourapi.di

import com.example.findyourapi.service.API.PublicApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder = Retrofit.Builder().baseUrl("https://api.publicapis.org/").addConverterFactory(GsonConverterFactory.create(gson))

    @Singleton
    @Provides
    fun providePublicApiService(retrofit: Retrofit.Builder): PublicApiService = retrofit.build().create(PublicApiService::class.java)

}