package com.plcoding.core.data.preferences

import android.content.SharedPreferences
import com.plcoding.core.domain.model.ActivityLevel
import com.plcoding.core.domain.model.Gender
import com.plcoding.core.domain.model.GoalType
import com.plcoding.core.domain.model.UserInfo
import com.plcoding.core.domain.preferences.Preferences

class DefaultPreferences(private val sharedPreferences: SharedPreferences): Preferences {
	override fun saveGender(gender: Gender) {
		sharedPreferences.edit()
			.putString(KEY_GENDER, gender.name)
			.apply()
	}

	override fun saveAge(age: Int) {
		sharedPreferences.edit()
			.putInt(KEY_AGE, age)
			.apply()
	}

	override fun saveWeight(weight: Float) {
		sharedPreferences.edit()
			.putFloat(KEY_WEIGHT, weight)
			.apply()
	}

	override fun saveHeight(height: Int) {
		sharedPreferences.edit()
			.putInt(KEY_HEIGHT, height)
			.apply()
	}

	override fun saveGoalType(goalType: GoalType) {
		sharedPreferences.edit()
			.putString(KEY_GOAL_TYPE, goalType.name)
			.apply()
	}

	override fun saveActivityLevel(activityLevel: ActivityLevel) {
		sharedPreferences.edit()
			.putString(KEY_ACTIVITY_LEVEL, activityLevel.name)
			.apply()
	}

	override fun saveCarbRatio(carbRatio: Float) {
		sharedPreferences.edit()
			.putFloat(KEY_CARB_RATIO, carbRatio)
			.apply()
	}

	override fun saveProteinRatio(proteinRatio: Float) {
		sharedPreferences.edit()
			.putFloat(KEY_PROTEIN_RATIO, proteinRatio)
			.apply()
	}

	override fun saveFatRatio(fatRatio: Float) {
		sharedPreferences.edit()
			.putFloat(KEY_FAT_RATIO, fatRatio)
			.apply()
	}

	override fun loadUserInfo(): UserInfo {
		val genderString = sharedPreferences.getString(KEY_GENDER, null)
		val age = sharedPreferences.getInt(KEY_AGE, -1)
		val weight = sharedPreferences.getFloat(KEY_WEIGHT, -1f)
		val height = sharedPreferences.getInt(KEY_HEIGHT, -1)
		val goalTypeString = sharedPreferences.getString(KEY_GOAL_TYPE, null)
		val activityLevelString = sharedPreferences.getString(KEY_ACTIVITY_LEVEL, null)
		val carbRatio = sharedPreferences.getFloat(KEY_CARB_RATIO, -1f)
		val proteinRatio = sharedPreferences.getFloat(KEY_PROTEIN_RATIO, -1f)
		val fatRatio = sharedPreferences.getFloat(KEY_FAT_RATIO, -1f)

		return UserInfo(
			gender = Gender.fromString(genderString ?: Gender.Male.name),
			age = age,
			weight = weight,
			height = height,
			goalType = GoalType.fromString(goalTypeString ?: GoalType.KeepWeight.name),
			activityLevel = ActivityLevel.fromString(activityLevelString ?: ActivityLevel.Medium.name),
			carbRatio = carbRatio,
			proteinRatio = proteinRatio,
			fatRatio = fatRatio
		)
	}

	companion object {
		private const val KEY_GENDER = "gender"
		private const val KEY_AGE = "age"
		private const val KEY_WEIGHT = "weight"
		private const val KEY_HEIGHT = "height"
		private const val KEY_GOAL_TYPE = "goal_type"
		private const val KEY_ACTIVITY_LEVEL = "activity_level"
		private const val KEY_CARB_RATIO = "carb_ratio"
		private const val KEY_PROTEIN_RATIO = "protein_ratio"
		private const val KEY_FAT_RATIO = "fat_ratio"
	}
}