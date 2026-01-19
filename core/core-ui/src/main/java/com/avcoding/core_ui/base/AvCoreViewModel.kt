package com.avcoding.core_ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class AvCoreViewModel<EVENT : UiEvent,  STATE : UiState >(
    initialState: STATE,
) : ViewModel() {

    protected val _state = MutableStateFlow(initialState)
    val state: StateFlow<STATE> = _state.asStateFlow()

    fun onEvent(event: EVENT) {
        handleEvent(event)
    }

    protected abstract fun handleEvent(event: EVENT)
}