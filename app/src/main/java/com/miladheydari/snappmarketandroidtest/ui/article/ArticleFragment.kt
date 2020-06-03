package com.miladheydari.snappmarketandroidtest.ui.article

import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miladheydari.snappmarketandroidtest.R
import com.miladheydari.snappmarketandroidtest.core.BaseFragment
import com.miladheydari.snappmarketandroidtest.core.Constants.NetworkService.PAGE_LIMIT
import com.miladheydari.snappmarketandroidtest.databinding.FragmentArticlesBinding

import com.miladheydari.snappmarketandroidtest.di.Injectable
import com.miladheydari.snappmarketandroidtest.domain.model.Article
import com.miladheydari.snappmarketandroidtest.domain.usecase.ArticleUseCase

import com.miladheydari.snappmarketandroidtest.ui.article.result.ArticleResultAdapter

import com.miladheydari.snappmarketandroidtest.utils.PaginationScrollListener
import com.miladheydari.snappmarketandroidtest.utils.extensions.isNetworkAvailable
import com.miladheydari.snappmarketandroidtest.utils.extensions.observeWith


class ArticleFragment : BaseFragment<ArticlesFragmentViewModel, FragmentArticlesBinding>
    (ArticlesFragmentViewModel::class.java), Injectable {


    private val articleFragmentArgs: ArticleFragmentArgs by navArgs()

    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false
    private var offset: Int = 1

    override fun getLayoutRes() = R.layout.fragment_articles

    override fun initViewModel() {
        mBinding.viewModel = viewModel
    }

    override fun init() {
        super.init()
        initAdapter()


        setViewModelObserver()

    }

    private fun initAdapter() {
        val adapter = ArticleResultAdapter()

        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        mBinding.recyclerView.adapter = adapter
        mBinding.recyclerView.layoutManager = layoutManager

        mBinding.recyclerView.addOnScrollListener(object :
            PaginationScrollListener(layoutManager) {
            override fun isLastPage(): Boolean {


                return false
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                if (isLastPage) {
                    return
                }
                isLoading = true
                offset +=1
                viewModel.setArticleParams(getParams())
            }
        })
    }

    private fun initArticleResultAdapter(list: List<Article>) {
        (mBinding.recyclerView.adapter as ArticleResultAdapter).submitList(list)
        isLoading = false
    }

    private fun setViewModelObserver() {
        viewModel.setArticleParams(getParams())

        viewModel.getArticleViewState().observeWith(viewLifecycleOwner) {
            mBinding.viewState = it
            it.data?.let { results -> initArticleResultAdapter(results) }
        }
    }

    private fun getParams(): ArticleUseCase.ArticleParams {
        return ArticleUseCase.ArticleParams(
            sourceId = articleFragmentArgs.sourceId,
            page = offset,
            pageSize = PAGE_LIMIT,
            fetchRequired = requireContext().isNetworkAvailable()
        )
    }


    override fun onDestroy() {
        mBinding.recyclerView.clearOnScrollListeners()
        super.onDestroy()
    }


}