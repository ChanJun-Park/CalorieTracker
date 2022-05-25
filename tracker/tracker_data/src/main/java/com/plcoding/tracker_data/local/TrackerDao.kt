package com.plcoding.tracker_data.local

import androidx.room.*
import com.plcoding.tracker_data.local.entity.TrackedFoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackerDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(trackedFoodEntity: TrackedFoodEntity)

	@Delete
	suspend fun delete(trackedFoodEntity: TrackedFoodEntity)

	@Query(
		"""
			SELECT *
			FROM TrackedFoodEntity
			WHERE dayOfMonth = :day AND month = :month AND year = :year
		"""
	)
	fun getFoodsForDay(day: Int, month: Int, year: Int): Flow<List<TrackedFoodEntity>>
}
