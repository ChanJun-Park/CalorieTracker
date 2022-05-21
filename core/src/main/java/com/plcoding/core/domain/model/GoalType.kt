package com.plcoding.core.domain.model

sealed class GoalType(val name: String) {
	object LoseWeight: GoalType(GOAL_TYPE_LOSE_WEIGHT_NAME)
	object KeepWeight: GoalType(GOAL_TYPE_KEEP_WEIGHT_NAME)
	object GainWeight: GoalType(GOAL_TYPE_GAIN_WEIGHT_NAME)

	companion object {
		private const val GOAL_TYPE_LOSE_WEIGHT_NAME = "lose_weight"
		private const val GOAL_TYPE_KEEP_WEIGHT_NAME = "keep_weight"
		private const val GOAL_TYPE_GAIN_WEIGHT_NAME = "gain_weight"

		fun fromString(name: String): GoalType {
			return when (name) {
				GOAL_TYPE_LOSE_WEIGHT_NAME -> LoseWeight
				GOAL_TYPE_KEEP_WEIGHT_NAME -> KeepWeight
				GOAL_TYPE_GAIN_WEIGHT_NAME -> GainWeight
				else -> throw IllegalArgumentException("GoalType name '$name' is not valid")
			}
		}
	}
}
