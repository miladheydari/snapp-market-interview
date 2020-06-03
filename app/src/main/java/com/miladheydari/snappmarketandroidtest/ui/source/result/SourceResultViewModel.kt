package com.miladheydari.snappmarketandroidtest.ui.source.result

import androidx.databinding.ObservableField
import com.miladheydari.snappmarketandroidtest.core.BaseViewModel
import com.miladheydari.snappmarketandroidtest.domain.model.Source
import javax.inject.Inject

class SourceResultViewModel @Inject internal constructor() : BaseViewModel() {
    var item = ObservableField<Source>()
}