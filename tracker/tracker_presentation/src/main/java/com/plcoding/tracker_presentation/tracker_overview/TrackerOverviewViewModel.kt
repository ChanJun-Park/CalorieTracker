package com.plcoding.tracker_presentation.tracker_overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.core.domain.preferences.Preferences
import com.plcoding.core.util.UiEvent
import com.plcoding.tracker_domain.use_case.TrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackerOverviewViewModel @Inject constructor(
	preferences: Preferences,
	private val trackerUseCases: TrackerUseCases
) : ViewModel() {

	var state by mutableStateOf(TrackerOverviewState())

	private val _uiEvent = Channel<UiEvent>()
	val uiEvent = _uiEvent.receiveAsFlow()

	init {
		refreshFoods()
		preferences.saveShouldShowOnboarding(false)
	}

	fun onEvent(event: TrackerOverviewEvent) {
		when (event) {
			is TrackerOverviewEvent.OnDeleteTrackedFoodClick -> {
				viewModelScope.launch {
					trackerUseCases.deleteTrackedFood(event.trackedFood)
					refreshFoods()
				}
			}
			is TrackerOverviewEvent.OnNextDayClick -> {
				state = state.copy(
					date = state.date.plusDays(1)
				)
				refreshFoods()
			}
			is TrackerOverviewEvent.OnPreviousDayClick -> {
				state = state.copy(
					date = state.date.minusDays(1)
				)
				refreshFoods()
			}
			is TrackerOverviewEvent.OnToggleMealClick -> {
				state = state.copy(
					meals = state.meals.map { it ->
						if (it.name == event.meal.name) {
							it.copy(isExpanded = !it.isExpanded)
						} else it
					}
				)
			}
		}
	}

	private fun refreshFoods() {
		trackerUseCases
			.getFoodsForDate(state.date)
			.onEach {
				val result = trackerUseCases.calculateMealNutrients(it)
				state = state.copy(
					totalCarbs = result.totalCarbs,
					totalProtein = result.totalProtein,
					totalFat = result.totalFat,
					totalCalories = result.totalCalories,
					carbsGoal = result.carbsGoal,
					proteinGoal = result.proteinGoal,
					fatGoal = result.fatGoal,
					caloriesGoal = result.caloriesGoal,
					trackedFoods = it,
					meals = state.meals.map { meal ->
						val mealNutrients = result.mealNutrients[meal.mealType]
							?: return@map meal.copy(carbs = 0, protein = 0, fat = 0, calories = 0)

						meal.copy(
							carbs = mealNutrients.carbs,
							protein = mealNutrients.protein,
							fat = mealNutrients.fat,
							calories = mealNutrients.calories
						)
					}
				)
			}
			.launchIn(viewModelScope)
	}
}