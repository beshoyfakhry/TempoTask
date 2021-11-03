package com.tempo.task.data.responses

import com.tempo.task.data.model.ArticleObject

data class ArticelsResponse(

    val status:String?="",
     val articles:MutableList<ArticleObject>
)
