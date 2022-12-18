package com.example.findyourapi.service.API

import com.example.findyourapi.model.APIs
import com.example.findyourapi.model.Categories
import retrofit2.http.GET

interface PublicApiService {

    @GET("entries")
    suspend fun getApis():APIs

    @GET("categories")
    suspend fun getCategories():Categories
}