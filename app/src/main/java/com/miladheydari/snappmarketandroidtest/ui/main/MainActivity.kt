package com.miladheydari.snappmarketandroidtest.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.gms.common.api.ResolvableApiException
import com.miladheydari.snappmarketandroidtest.R
import com.miladheydari.snappmarketandroidtest.core.BaseActivity
import com.miladheydari.snappmarketandroidtest.databinding.ActivityMainBinding
import com.miladheydari.snappmarketandroidtest.utils.ActivityResultCallback
import com.miladheydari.snappmarketandroidtest.utils.extensions.hide
import com.miladheydari.snappmarketandroidtest.utils.extensions.show
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>
    (MainActivityViewModel::class.java), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>
    private lateinit var activityResultCallback: ActivityResultCallback

    override fun androidInjector() = androidInjector

    override fun initViewModel(viewModel: MainActivityViewModel) {
        binding.viewModel = viewModel
    }

    override fun getLayoutRes() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)

        setupNavigation()
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.container_fragment)
        setupActionBarWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment -> {
                    binding.appBarLayout.hide()
                }
                R.id.sourceFragment -> {
                    setupToolbarTitleAndAction(getString(R.string.sources), false)
                }
                R.id.articleFragment -> {
                    setupToolbarTitleAndAction(getString(R.string.article), true)
                }
            }
        }
    }

    private fun setupToolbarTitleAndAction(title: String, isDisplayHomeAsUp: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(isDisplayHomeAsUp)
        viewModel.toolbarTitle.set(title)
        binding.appBarLayout.show()
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.container_fragment).navigateUp()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        activityResultCallback.onResult(requestCode, resultCode, data)
    }
}
