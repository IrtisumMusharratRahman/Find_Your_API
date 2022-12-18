package com.example.findyourapi.service.repository

import com.example.findyourapi.model.APIs
import com.example.findyourapi.model.Categories
import com.example.findyourapi.service.API.PublicApiService

class PublicApiRepository(
    private val publicApiService: PublicApiService
) {

    suspend fun getApis(): APIs = publicApiService.getApis()

    suspend fun getCategories(): Categories = publicApiService.getCategories()

}