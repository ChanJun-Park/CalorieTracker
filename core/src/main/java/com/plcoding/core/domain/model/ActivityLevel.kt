package com.plcoding.core.domain.model

sealed class ActivityLevel(val name: String) {
	object Low: ActivityLevel(ACTIVITY_LEVEL_LOW_NAME)
	object Medium: ActivityLevel(ACTIVITY_LEVEL_MEDIUM_NAME)
	object High: ActivityLevel(ACTIVITY_LEVEL_HIGH_NAME)

	companion object {
		private const val ACTIVITY_LEVEL_LOW_NAME = "low"
		private const val ACTIVITY_LEVEL_MEDIUM_NAME = "medium"
		private const val ACTIVITY_LEVEL_HIGH_NAME = "high"

		fun fromString(name: String): ActivityLevel {
			return when (name) {
				ACTIVITY_LEVEL_LOW_NAME -> Low
				ACTIVITY_LEVEL_MEDIUM_NAME -> Medium
				ACTIVITY_LEVEL_HIGH_NAME -> High
				else -> throw IllegalArgumentException("ActivityLevel name '$name' is not valid")
			}
		}
	}
}
