package com.miladheydari.snappmarketandroidtest.ui.source


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miladheydari.snappmarketandroidtest.core.BaseFragment
import com.miladheydari.snappmarketandroidtest.databinding.FragmentSourcesBinding
import com.miladheydari.snappmarketandroidtest.ui.source.result.SourceResultAdapter
import com.miladheydari.snappmarketandroidtest.utils.extensions.isNetworkAvailable
import com.miladheydari.snappmarketandroidtest.utils.extensions.observeWith
import com.snapp.domain.interactor.SourcesUseCase
import com.snapp.domain.models.Source
import com.snapp.presentation.viewmodel.SourcesFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SourceFragment : BaseFragment<FragmentSourcesBinding, SourcesFragmentViewModel>() {


    private var isLoading: Boolean = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()


        setViewModelObserver()


    }

    private fun initAdapter() {
        val adapter = SourceResultAdapter() { item ->
            findNavController().navigate(
                SourceFragmentDirections.actionSourceFragmentToArticleFragment(item.id, item.name)
            )
        }
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = layoutManager


    }

    private fun initSourceResultAdapter(list: List<Source>) {
        (binding.recyclerView.adapter as SourceResultAdapter).submitList(list)
        isLoading = false
    }

    private fun setViewModelObserver() {
        viewModel.setSourceParams(getParams())

        viewModel.getSourceViewState().observeWith(viewLifecycleOwner) {
            binding.viewState = it
            it.data?.let { results -> initSourceResultAdapter(results) }
        }
    }

    private fun getParams(): SourcesUseCase.SourceParams {
        return SourcesUseCase.SourceParams(

            fetchRequired = requireContext().isNetworkAvailable()
        )
    }


    override fun onDestroy() {
        binding.recyclerView.clearOnScrollListeners()


        super.onDestroy()
    }

    override val viewModel: SourcesFragmentViewModel by viewModels()


    override fun getViewBinding(): FragmentSourcesBinding {
        return FragmentSourcesBinding.inflate(layoutInflater)
    }


}