package com.snapp.presentation.viewmodel

import androidx.databinding.ObservableField
import com.snapp.domain.models.Source
import javax.inject.Inject

class SourceResultViewModel @Inject internal constructor() : BaseViewModel() {
    var item = ObservableField<Source>()
}