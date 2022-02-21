package com.snapp.presentation.viewmodel

import androidx.databinding.ObservableField
import javax.inject.Inject

class MainActivityViewModel @Inject internal constructor() : BaseViewModel() {
    var toolbarTitle: ObservableField<String> = ObservableField()
}
