package com.miladheydari.snappmarketandroidtest.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.miladheydari.snappmarketandroidtest.di.ViewModelFactory
import com.miladheydari.snappmarketandroidtest.di.key.ViewModelKey

import com.miladheydari.snappmarketandroidtest.ui.article.ArticlesFragmentViewModel

import com.miladheydari.snappmarketandroidtest.ui.article.result.ArticleResultViewModel
import com.miladheydari.snappmarketandroidtest.ui.main.MainActivityViewModel
import com.miladheydari.snappmarketandroidtest.ui.source.SourcesFragmentViewModel
import com.miladheydari.snappmarketandroidtest.ui.splash.SplashFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @Binds
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun provideMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(SplashFragmentViewModel::class)
    abstract fun provideSplashFragmentViewModel(splashFragmentViewModel: SplashFragmentViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(ArticlesFragmentViewModel::class)
    abstract fun provideArticleFragmentViewModel(articlesFragmentViewModel: ArticlesFragmentViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(ArticleResultViewModel::class)
    abstract fun provideArticleResultViewModel(articleResultViewModel: ArticleResultViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(SourcesFragmentViewModel::class)
    abstract fun provideSourceFragmentViewModel(sourceFragmentViewModel: SourcesFragmentViewModel): ViewModel
}
