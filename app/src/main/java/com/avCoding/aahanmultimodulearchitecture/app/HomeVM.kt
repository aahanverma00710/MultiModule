package com.avCoding.aahanmultimodulearchitecture.app

import com.avcoding.core_ui.base.AvCoreViewModel
import com.avcoding.core_ui.base.UiEvent
import com.avcoding.core_ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed interface HomeState : UiState {
    object Loading : HomeState
}

@HiltViewModel
class HomeVM @Inject constructor() : AvCoreViewModel<UiEvent, HomeState>(
    HomeState.Loading
){


    override fun handleEvent(event: UiEvent) {

    }


}