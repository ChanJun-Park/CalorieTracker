package com.plcoding.tracker_domain.use_case

import com.plcoding.tracker_domain.model.TrackedFood
import com.plcoding.tracker_domain.repository.TrackerRepository

class TrackFood(
	private val repository: TrackerRepository
) {
	suspend operator fun invoke(trackedFood: TrackedFood) {
		repository.insertTrackedFood(trackedFood)
	}
}