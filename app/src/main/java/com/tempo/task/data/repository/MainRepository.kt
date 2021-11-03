package com.tempo.task.data.repository

import com.tempo.task.data.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getArticles(query: String) = apiHelper.getArticles(query)

}