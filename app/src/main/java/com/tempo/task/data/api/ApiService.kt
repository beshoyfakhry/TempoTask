package com.tempo.task.data.api


import com.tempo.task.BuildConfig
import com.tempo.task.data.responses.ArticelsResponse

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val API_KEY = BuildConfig.API_KEY
    }

    @GET("everything")
    suspend fun getArticles(
        @Query("q") query: String,
        @Query("from") from: String = "2021-10-10",
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<ArticelsResponse>
}

