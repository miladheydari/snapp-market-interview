package com.miladheydari.snappmarketandroidtest.core

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.snapp.presentation.viewmodel.BaseViewModel

abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> :
    AppCompatActivity() {


    @LayoutRes
    abstract fun getLayoutRes(): Int

    val binding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as DB
    }

   abstract val viewModel :VM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setupBindingLifecycleOwner()
    }

    /**
     *
     *  You need override this method.
     *  And you need to set viewModel to binding: binding.viewModel = viewModel
     *
     */


    private fun setupBindingLifecycleOwner() {
        binding.lifecycleOwner = this
    }
}
