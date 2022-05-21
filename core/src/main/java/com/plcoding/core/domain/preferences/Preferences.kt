package com.plcoding.core.domain.preferences

import com.plcoding.core.domain.model.ActivityLevel
import com.plcoding.core.domain.model.Gender
import com.plcoding.core.domain.model.GoalType
import com.plcoding.core.domain.model.UserInfo

interface Preferences {
	fun saveGender(gender: Gender)
	fun saveAge(age: Int)
	fun saveWeight(weight: Float)
	fun saveHeight(height: Int)
	fun saveGoalType(goalType: GoalType)
	fun saveActivityLevel(activityLevel: ActivityLevel)
	fun saveCarbRatio(carbRatio: Float)
	fun saveProteinRatio(proteinRatio: Float)
	fun saveFatRatio(fatRatio: Float)

	fun loadUserInfo(): UserInfo
}