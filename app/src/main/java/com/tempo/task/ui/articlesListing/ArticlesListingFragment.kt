package com.tempo.task.ui.articlesListing

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.tempo.task.R
import com.tempo.task.data.model.ArticleObject
import com.tempo.task.ui.articleDetail.ArticleDetailsFragmentArgs
import com.tempo.task.utils.Extensions.hideKeyboard
import com.tempo.task.utils.Utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.articles_listing_fragment.*


@AndroidEntryPoint
class ArticlesListingFragment : Fragment(R.layout.articles_listing_fragment),
    ArticlesAdapter.OnItemClickListener {
    private val mainViewModel: ArticlesListingViewModel by viewModels()
    lateinit var articlesAdapter: ArticlesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articlesAdapter = ArticlesAdapter(this)
        rv_articles.apply {
            adapter = articlesAdapter
            setHasFixedSize(true)
        }

        setObserver()
        setScrollListener()

        et_search_query.setOnEditorActionListener { v: TextView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (et_search_query.text.isNotEmpty()) {
                    v.hideKeyboard()
                    getArticles(et_search_query.text.toString())

                }
                else
                {
                    Snackbar.make(root_view, getString(R.string.search_date_missing), Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
            true
        }

        et_search_query.doAfterTextChanged {
            if (it.toString().isEmpty()) {
                mainViewModel.clearAllData()
            }
        }

    }

    private fun getArticles(query: String) {
        mainViewModel.getAllArticles(query)
    }

    private fun setObserver() {
        mainViewModel.subArticlesLive.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    rv_articles.visibility = View.VISIBLE
                    progress.visibility = View.GONE
                    articlesAdapter.submitList(it.data!!.articles)
                    articlesAdapter.notifyDataSetChanged()
//                    it.data.let { res ->
//                        if (res?.status == "success") {
//                               res.articles?.let { it1 -> articlesAdapter.submitList(it1) }
//                        } else {
////                        Snackbar.make(rootView, "Status = false",Snackbar.LENGTH_SHORT).show()
//                        }
//                    }
                }
                Status.LOADING -> {
                    progress.visibility = View.VISIBLE
                    rv_articles.visibility = View.GONE
                }
                Status.CLEAR -> {
                    articlesAdapter.submitList(it.data!!.articles)
                    articlesAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    progress.visibility = View.GONE
                    rv_articles.visibility = View.VISIBLE
                    Snackbar.make(root_view, "Something went wrong", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        })

    }

    private fun setScrollListener() {
        rv_articles.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) { //1 for down
                    mainViewModel.getSubPages()
                }
            }
        })
    }

    override fun onItemClick(article: ArticleObject) {

        val action = ArticlesListingFragmentDirections.actionNewsListingToNewsDetails(article)
        requireView().findNavController().navigate(action)


    }


}