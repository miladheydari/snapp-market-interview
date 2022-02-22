package com.snapp.presentation.viewmodel

import androidx.databinding.ObservableField
import com.snapp.domain.models.Source
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class SourceResultViewModel : BaseViewModel() {
    var item = ObservableField<Source>()
}