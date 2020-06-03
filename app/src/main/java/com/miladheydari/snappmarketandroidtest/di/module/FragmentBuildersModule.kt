package com.miladheydari.snappmarketandroidtest.di.module

import com.miladheydari.snappmarketandroidtest.ui.article.ArticleFragment
import com.miladheydari.snappmarketandroidtest.ui.source.SourceFragment
import com.miladheydari.snappmarketandroidtest.ui.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun contributeArticleFragment(): ArticleFragment

    @ContributesAndroidInjector
    abstract fun contributeSourceFragment(): SourceFragment
}
