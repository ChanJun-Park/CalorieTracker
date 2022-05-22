package com.plcoding.core.util

import android.content.Context
import androidx.annotation.StringRes

sealed class UiText {
	data class DynamicClass(val string: String): UiText()
	data class StringResource(@StringRes val resId: Int): UiText()

	fun asString(context: Context): String {
		return when (this) {
			is DynamicClass -> string
			is StringResource -> context.getString(resId)
		}
	}
}