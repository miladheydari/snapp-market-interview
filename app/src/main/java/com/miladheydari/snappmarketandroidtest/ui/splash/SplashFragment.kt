package com.miladheydari.snappmarketandroidtest.ui.splash

import androidx.navigation.fragment.findNavController
import com.miladheydari.snappmarketandroidtest.R
import com.miladheydari.snappmarketandroidtest.core.BaseFragment
import com.miladheydari.snappmarketandroidtest.databinding.FragmentSplashBinding
import com.miladheydari.snappmarketandroidtest.di.Injectable
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class SplashFragment : BaseFragment<SplashFragmentViewModel, FragmentSplashBinding>
    (SplashFragmentViewModel::class.java), Injectable {

    var disposable: Disposable? = null

    override fun getLayoutRes() = R.layout.fragment_splash

    override fun initViewModel() {
        mBinding.viewModel = viewModel
    }

    override fun init() {
        super.init()

        disposable = Completable.timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .subscribe({
                showNextActivity()
            }, {})
    }

    private fun showNextActivity() {
        findNavController().graph.startDestination = R.id.sourceFragment

        findNavController().navigate(
            SplashFragmentDirections.actionSplashFragmentToSourceFragment()
        )
    }

    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }
}
