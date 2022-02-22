package com.miladheydari.snappmarketandroidtest.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.miladheydari.snappmarketandroidtest.R
import com.miladheydari.snappmarketandroidtest.core.BaseFragment
import com.miladheydari.snappmarketandroidtest.databinding.FragmentSplashBinding
import com.snapp.presentation.viewmodel.SplashFragmentViewModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashFragmentViewModel>() {

    var disposable: Disposable? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        disposable = Completable.timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .subscribe({
                showNextActivity()
            }, {})
    }

    private fun showNextActivity() {
//        findNavController().graph.startDestinationId = R.id.sourceFragment

        findNavController().navigate(
            SplashFragmentDirections.actionSplashFragmentToSourceFragment()
        )
    }

    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }

    override val viewModel: SplashFragmentViewModel by viewModels()

    override fun getViewBinding(): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(layoutInflater)
    }
}
