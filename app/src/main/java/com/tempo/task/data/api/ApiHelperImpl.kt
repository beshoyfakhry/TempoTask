package com.tempo.task.data.api

import com.tempo.task.data.responses.ArticelsResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {


    override suspend fun getArticles(query: String): Response<ArticelsResponse> =
        apiService.getArticles(query)
}