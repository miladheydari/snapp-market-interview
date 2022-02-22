package com.miladheydari.snappmarketandroidtest.ui.source.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.miladheydari.snappmarketandroidtest.core.BaseAdapter
import com.miladheydari.snappmarketandroidtest.databinding.ItemSourceBinding
import com.snapp.domain.models.Source
import com.snapp.presentation.viewmodel.SourceResultViewModel


class SourceResultAdapter(
    private val callBack: (Source) -> Unit
) : BaseAdapter<Source>(diffCallback) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val mBinding = ItemSourceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val viewModel = SourceResultViewModel()
        mBinding.viewModel = viewModel
        mBinding.rootItemView.setOnClickListener {
            mBinding.viewModel?.item?.get()?.let {
                callBack.invoke(it)
            }
        }

        return mBinding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as ItemSourceBinding).viewModel?.item?.set(getItem(position))
        binding.executePendingBindings()
    }
}

val diffCallback = object : DiffUtil.ItemCallback<Source>() {
    override fun areContentsTheSame(oldItem: Source, newItem: Source): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(oldItem: Source, newItem: Source): Boolean =
        oldItem.id == newItem.id
}
