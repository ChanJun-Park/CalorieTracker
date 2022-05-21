package com.plcoding.core.domain.model

sealed class Gender(val name: String) {
	object Male: Gender(GENDER_MALE_NAME)
	object Female: Gender(GENDER_FEMALE_NAME)

	companion object {
		private const val GENDER_MALE_NAME = "male"
		private const val GENDER_FEMALE_NAME = "female"

		fun fromString(name: String): Gender {
			return when (name) {
				GENDER_MALE_NAME -> Male
				GENDER_FEMALE_NAME -> Female
				else -> throw IllegalArgumentException("Gender name '$name' is not valid")
			}
		}
	}
}
