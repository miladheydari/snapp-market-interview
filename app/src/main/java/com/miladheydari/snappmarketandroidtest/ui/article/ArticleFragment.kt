package com.miladheydari.snappmarketandroidtest.ui.article


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miladheydari.snappmarketandroidtest.core.BaseFragment
import com.miladheydari.snappmarketandroidtest.core.Constants.NetworkService.PAGE_LIMIT
import com.miladheydari.snappmarketandroidtest.databinding.FragmentArticlesBinding
import com.miladheydari.snappmarketandroidtest.ui.article.result.ArticleResultAdapter
import com.miladheydari.snappmarketandroidtest.utils.PaginationScrollListener
import com.miladheydari.snappmarketandroidtest.utils.extensions.isNetworkAvailable
import com.miladheydari.snappmarketandroidtest.utils.extensions.observeWith
import com.snapp.domain.interactor.ArticleUseCase
import com.snapp.domain.models.Article
import com.snapp.presentation.viewmodel.ArticleResultViewModel
import com.snapp.presentation.viewmodel.ArticlesFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ArticleFragment : BaseFragment<FragmentArticlesBinding, ArticlesFragmentViewModel>() {


    private val articleFragmentArgs: ArticleFragmentArgs by navArgs()

    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false
    private var offset: Int = 1


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        initAdapter()
        setViewModelObserver()

    }

    private fun initAdapter() {
        val adapter = ArticleResultAdapter()

        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = layoutManager

        binding.recyclerView.addOnScrollListener(object :
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
                offset += 1
                viewModel.setArticleParams(getParams())
            }
        })
    }

    private fun initArticleResultAdapter(list: List<Article>) {
        (binding.recyclerView.adapter as ArticleResultAdapter).submitList(list)
        isLoading = false
    }

    private fun setViewModelObserver() {
        viewModel.setArticleParams(getParams())

        viewModel.getArticleViewState().observeWith(viewLifecycleOwner) {
            binding.viewState = it
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
        binding.recyclerView.clearOnScrollListeners()
        super.onDestroy()
    }

    override val viewModel: ArticlesFragmentViewModel by viewModels()
    override fun getViewBinding(): FragmentArticlesBinding {
        return FragmentArticlesBinding.inflate(layoutInflater)
    }


}