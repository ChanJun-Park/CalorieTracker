package com.plcoding.core.util

sealed class UiEvent {
	object Success : UiEvent()
	object NavigateUp : UiEvent()
	data class ShowSnackbar(val uiText: UiText) : UiEvent()
}