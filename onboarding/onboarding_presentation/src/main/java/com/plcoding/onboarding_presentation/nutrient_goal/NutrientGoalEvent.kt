package com.plcoding.onboarding_presentation.nutrient_goal

sealed class NutrientGoalEvent {
	data class OnCarbsRatioEnter(val carbsRatio: String): NutrientGoalEvent()
	data class OnProteinRatioEnter(val proteinRatio: String): NutrientGoalEvent()
	data class OnFatRatioEnter(val fatRatio: String): NutrientGoalEvent()
	object OnNextClick: NutrientGoalEvent()
}