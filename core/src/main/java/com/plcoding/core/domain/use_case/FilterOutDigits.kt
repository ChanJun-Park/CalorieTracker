package com.plcoding.core.domain.use_case

import java.lang.Character.isDigit

class FilterOutDigits {

	operator fun invoke(text: String): String {
		return text.filter { isDigit(it) }
	}

}