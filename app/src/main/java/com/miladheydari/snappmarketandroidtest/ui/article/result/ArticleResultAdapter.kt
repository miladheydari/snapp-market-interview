package com.miladheydari.snappmarketandroidtest.ui.article.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.miladheydari.snappmarketandroidtest.core.BaseAdapter
import com.miladheydari.snappmarketandroidtest.databinding.ItemArticleBinding
import com.snapp.domain.models.Article
import com.snapp.presentation.viewmodel.ArticleResultViewModel
import com.snapp.presentation.viewmodel.SourceResultViewModel
import javax.inject.Inject


class ArticleResultAdapter() : BaseAdapter<Article>(diffCallback) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val mBinding = ItemArticleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val viewModel = ArticleResultViewModel()

        mBinding.viewModel = viewModel

        return mBinding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as ItemArticleBinding).viewModel?.item?.set(getItem(position))
        binding.executePendingBindings()
    }
}

val diffCallback = object : DiffUtil.ItemCallback<Article>() {
    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
        oldItem.title + oldItem.publishedAt == newItem.title + newItem.publishedAt
}
