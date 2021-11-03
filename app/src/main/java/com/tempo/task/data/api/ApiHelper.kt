package com.tempo.task.data.api

import com.tempo.task.data.responses.ArticelsResponse
import retrofit2.Response

interface ApiHelper {

    suspend fun getArticles(query: String): Response<ArticelsResponse>
}