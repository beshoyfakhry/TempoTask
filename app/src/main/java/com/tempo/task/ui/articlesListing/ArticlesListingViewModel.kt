package com.tempo.task.ui.articlesListing

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tempo.task.data.model.ArticleObject
import com.tempo.task.data.repository.MainRepository
import com.tempo.task.data.responses.ArticelsResponse
import com.tempo.task.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesListingViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    val subArticlesLive = MutableLiveData<Resource<ArticelsResponse>>()
    private lateinit var allArticles: MutableList<ArticleObject>
    private val subList = mutableListOf<ArticleObject>()

    private var startIndex = 0
    private var endIndex = 0
    private val pageSize = 70


//    val res: LiveData<Resource<ArticelsResponse>>
//        get() = subArticles


    fun getAllArticles(searchQuery: String) = viewModelScope.launch {
        startIndex = 0
        endIndex = 0
        subList.clear()
        subArticlesLive.postValue(Resource.loading(null))

        mainRepository.getArticles(searchQuery).let {
            if (it.isSuccessful) {
                allArticles = it.body()!!.articles
                getSubPages()
            } else {
                subArticlesLive.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }

    fun getSubPages() {
        subArticlesLive.postValue(Resource.loading(null))

        if (startIndex + pageSize >= allArticles.size) {
            endIndex = allArticles.size - 1
        } else {
            endIndex = startIndex + pageSize
        }


        subList.addAll(allArticles.subList(startIndex, endIndex))

        val resourceResponse = ArticelsResponse("", subList)
        subArticlesLive.postValue(Resource.success(resourceResponse))
        startIndex = endIndex


    }


    fun clearAllData() {
        subList.clear()
        val resourceResponse = ArticelsResponse("", subList)

        subArticlesLive.postValue(Resource.clearData(resourceResponse))
    }

}