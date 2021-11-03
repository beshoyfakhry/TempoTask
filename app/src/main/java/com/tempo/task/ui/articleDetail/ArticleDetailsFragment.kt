package com.tempo.task.ui.articleDetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.tempo.task.R
import com.tempo.task.data.model.ArticleObject
import com.tempo.task.databinding.ArticleDetailsFragmentBinding
import com.tempo.task.utils.Extensions.dateToAgo
import com.tempo.task.utils.Extensions.loadImage
import kotlinx.android.synthetic.main.article_details_fragment.*


class ArticleDetailsFragment : Fragment(R.layout.article_details_fragment) {

    private lateinit var binding: ArticleDetailsFragmentBinding

    private val args: ArticleDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = ArticleDetailsFragmentBinding.bind(view)

        val article = args.selectedArticle


        binding.apply {
            tvNewsTitle.text = article.title
            tvNewsDesc.text = article.description
            tvNewsDate.text = article.publishedAt.dateToAgo()


            ivArticleImage.loadImage(article.urlToImage)
            tvNewsAuthor.text = article.author
            tvNewsDesc.text = article.description
            tvContent.text = article.content
            fab_source.setOnClickListener {
                moveToSource(article)
            }


        }

    }

    private fun moveToSource(article: ArticleObject) = try {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(article.url)))
    } catch (e: Exception) {
        Snackbar.make(binding.rootView, getString(R.string.corrupted_source), Snackbar.LENGTH_SHORT)
            .show()

    }


}