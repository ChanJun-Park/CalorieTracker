package com.plcoding.tracker_domain.use_case

import com.google.common.truth.Truth.assertThat
import com.plcoding.core.domain.model.ActivityLevel
import com.plcoding.core.domain.model.Gender
import com.plcoding.core.domain.model.GoalType
import com.plcoding.core.domain.model.UserInfo
import com.plcoding.core.domain.preferences.Preferences
import com.plcoding.tracker_domain.model.MealType
import com.plcoding.tracker_domain.model.TrackedFood
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import kotlin.random.Random

class CalculateMealNutrientsTest {

	private lateinit var calculateMealNutrients: CalculateMealNutrients
	private lateinit var preferences: Preferences

	@Before
	fun setup() {
		preferences = mockk(relaxed = true)
		every { preferences.loadUserInfo() } returns UserInfo(
			gender = Gender.Male,
			age = 29,
			weight = 70f,
			height = 172,
			goalType = GoalType.LoseWeight,
			activityLevel = ActivityLevel.Low,
			carbRatio = 0.4f,
			proteinRatio = 0.3f,
			fatRatio = 0.3f
		)

		calculateMealNutrients = CalculateMealNutrients(preferences)
	}

	@Test
	fun `CalculateMealNutrients calculate breakfast calories properly`() {
		val trackedFoods = (1..30).map {
			TrackedFood(
				name = "name",
				carbs = Random.nextInt(500),
				protein = Random.nextInt(500),
				fat = Random.nextInt(500),
				imageUrl = null,
				mealType = MealType.fromString(
					listOf("breakfast", "lunch", "dinner", "snack").random()
				),
				amount = Random.nextInt(2000),
				date = LocalDate.now(),
				calories = Random.nextInt(2000)
			)
		}

		val result = calculateMealNutrients(trackedFoods)

		val breakfastCalories = result.mealNutrients.values
			.filter {
				it.mealType is MealType.Breakfast
			}.sumOf {
				it.calories
			}

		val expectedCalories = trackedFoods
			.filter {
				it.mealType is MealType.Breakfast
			}.sumOf {
				it.calories
			}

		assertThat(breakfastCalories).isEqualTo(expectedCalories)
	}

	@Test
	fun `Carbs for dinner properly calculated`() {
		val trackedFoods = (1..30).map {
			TrackedFood(
				name = "name",
				carbs = Random.nextInt(500),
				protein = Random.nextInt(500),
				fat = Random.nextInt(500),
				imageUrl = null,
				mealType = MealType.fromString(
					listOf("breakfast", "lunch", "dinner", "snack").random()
				),
				amount = Random.nextInt(2000),
				date = LocalDate.now(),
				calories = Random.nextInt(2000)
			)
		}

		val result = calculateMealNutrients(trackedFoods)

		val dinnerCarbs = result.mealNutrients.values
			.filter {
				it.mealType is MealType.Dinner
			}.sumOf {
				it.carbs
			}

		val expectedCarbs = trackedFoods
			.filter {
				it.mealType is MealType.Dinner
			}.sumOf {
				it.carbs
			}

		assertThat(dinnerCarbs).isEqualTo(expectedCarbs)
	}

}