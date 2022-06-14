package com.plcoding.tracker_data.repository

import com.plcoding.tracker_data.local.TrackerDao
import com.plcoding.tracker_data.mapper.toTrackableFood
import com.plcoding.tracker_data.mapper.toTrackedFood
import com.plcoding.tracker_data.mapper.toTrackedFoodEntity
import com.plcoding.tracker_data.remote.OpenFoodApi
import com.plcoding.tracker_domain.model.TrackableFood
import com.plcoding.tracker_domain.model.TrackedFood
import com.plcoding.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class TrackerRepositoryImpl(
	private val api: OpenFoodApi,
	private val dao: TrackerDao
) : TrackerRepository {

	override suspend fun searchFood(query: String, page: Int, pageSize: Int): Result<List<TrackableFood>> {
		return try {
			val result = api.searchFood(
				query = query,
				page = page,
				pageSize = pageSize
			)
				.products
				.filter {
					val calculatedCalories = it.nutriments.carbohydrates100g * 4 +
							it.nutriments.proteins100g * 4 +
							it.nutriments.fat100g * 9
					val lowerBound = calculatedCalories * 0.99f
					val upperBound = calculatedCalories * 1.01f

					it.nutriments.energyKcal100g in lowerBound..upperBound
				}
				.mapNotNull {
					it.toTrackableFood()
				}

			Result.success(result)
		} catch (e: Exception) {
			Result.failure(e)
		}
	}

	override suspend fun insertTrackedFood(food: TrackedFood) {
		dao.insert(food.toTrackedFoodEntity())
	}

	override suspend fun deleteTrackedFood(food: TrackedFood) {
		dao.delete(food.toTrackedFoodEntity())
	}

	override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
		return dao.getFoodsForDay(
			day = localDate.dayOfMonth,
			month = localDate.monthValue,
			year = localDate.year
		).map { entities ->
			entities.map { it.toTrackedFood() }
		}
	}
}