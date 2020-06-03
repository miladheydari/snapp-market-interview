package com.miladheydari.snappmarketandroidtest.ui.main

import androidx.databinding.ObservableField
import com.miladheydari.snappmarketandroidtest.core.BaseViewModel
import javax.inject.Inject

class MainActivityViewModel @Inject internal constructor() : BaseViewModel() {
    var toolbarTitle: ObservableField<String> = ObservableField()
}
