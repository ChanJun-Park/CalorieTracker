package com.plcoding.tracker_domain.model

sealed class MealType(val name: String) {
	object Breakfast: MealType(NAME_BREAKFAST)
	object Lunch: MealType(NAME_LUNCH)
	object Dinner: MealType(NAME_DINNER)
	object Snack: MealType(NAME_SNACK)

	companion object {

		private const val NAME_BREAKFAST = "breakfast"
		private const val NAME_LUNCH = "lunch"
		private const val NAME_DINNER = "dinner"
		private const val NAME_SNACK = "snack"

		fun fromString(name: String): MealType {
			return when (name) {
				NAME_BREAKFAST -> Breakfast
				NAME_LUNCH -> Lunch
				NAME_DINNER -> Dinner
				NAME_SNACK -> Snack
				else -> Breakfast
			}
		}
	}
}
