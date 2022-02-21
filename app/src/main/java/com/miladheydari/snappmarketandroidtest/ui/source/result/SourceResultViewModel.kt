package com.miladheydari.snappmarketandroidtest.ui.source.result

import androidx.databinding.ObservableField
import com.snapp.remote.models.Source
import javax.inject.Inject

class SourceResultViewModel @Inject internal constructor() : BaseViewModel() {
    var item = ObservableField<com.snapp.remote.models.Source>()
}