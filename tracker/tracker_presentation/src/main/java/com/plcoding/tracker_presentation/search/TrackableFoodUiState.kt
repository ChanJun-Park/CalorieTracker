package com.plcoding.tracker_presentation.search

import com.plcoding.tracker_domain.model.TrackableFood

data class TrackableFoodUiState(
	val trackableFood: TrackableFood,
	val isExpanded: Boolean = false,
	val amount: String = ""
)
