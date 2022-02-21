package com.miladheydari.snappmarketandroidtest.ui.source

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miladheydari.snappmarketandroidtest.R
import com.miladheydari.snappmarketandroidtest.core.BaseFragment
import com.miladheydari.snappmarketandroidtest.databinding.FragmentSourcesBinding
import com.miladheydari.snappmarketandroidtest.di.Injectable
import com.snapp.remote.models.Source
import com.snapp.domain.interactor.SourcesUseCase

import com.miladheydari.snappmarketandroidtest.ui.source.result.SourceResultAdapter
import com.miladheydari.snappmarketandroidtest.utils.extensions.isNetworkAvailable
import com.miladheydari.snappmarketandroidtest.utils.extensions.observeWith


class SourceFragment : BaseFragment<SourcesFragmentViewModel, FragmentSourcesBinding>
    (SourcesFragmentViewModel::class.java), Injectable {




    private var isLoading: Boolean = false


    override fun getLayoutRes() = R.layout.fragment_sources

    override fun initViewModel() {
        mBinding.viewModel = viewModel
    }

    override fun init() {
        super.init()
        initAdapter()


        setViewModelObserver()

    }

    private fun initAdapter() {
        val adapter = SourceResultAdapter{ item ->
            findNavController().navigate(
                SourceFragmentDirections.actionSourceFragmentToArticleFragment(item.id,item.name)
            )
        }
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        mBinding.recyclerView.adapter = adapter
        mBinding.recyclerView.layoutManager = layoutManager


    }

    private fun initSourceResultAdapter(list: List<com.snapp.remote.models.Source>) {
        (mBinding.recyclerView.adapter as SourceResultAdapter).submitList(list)
        isLoading = false
    }

    private fun setViewModelObserver() {
        viewModel.setSourceParams(getParams())

        viewModel.getSourceViewState().observeWith(viewLifecycleOwner) {
            mBinding.viewState = it
            it.data?.let { results -> initSourceResultAdapter(results) }
        }
    }

    private fun getParams(): com.snapp.domain.interactor.SourcesUseCase.SourceParams {
        return com.snapp.domain.interactor.SourcesUseCase.SourceParams(

            fetchRequired = requireContext().isNetworkAvailable()
        )
    }


    override fun onDestroy() {
        mBinding.recyclerView.clearOnScrollListeners()


        super.onDestroy()
    }


}