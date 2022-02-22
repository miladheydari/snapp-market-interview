package com.snapp.presentation.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashFragmentViewModel @Inject constructor() : BaseViewModel() {
    var navigateDashboard = false
}
