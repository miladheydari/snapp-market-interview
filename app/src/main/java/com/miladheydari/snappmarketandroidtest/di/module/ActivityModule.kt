package com.miladheydari.snappmarketandroidtest.di.module

import com.miladheydari.snappmarketandroidtest.ui.main.MainActivity
import com.miladheydari.snappmarketandroidtest.di.scope.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    /**
     * Injects [MainActivity]
     *
     * @return an instance of [MainActivity]
     */

    @PerActivity
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    internal abstract fun mainActivity(): MainActivity
}
