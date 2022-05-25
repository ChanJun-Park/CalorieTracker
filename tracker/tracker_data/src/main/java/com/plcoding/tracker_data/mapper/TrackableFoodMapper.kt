package com.plcoding.tracker_data.mapper

import com.plcoding.tracker_data.remote.dto.Product
import com.plcoding.tracker_domain.model.TrackableFood
import kotlin.math.roundToInt

fun Product.toTrackableFood(): TrackableFood? {
	return TrackableFood(
		name = productName ?: return null,
		imageUrl = imageFrontThumbUrl,
		caloriesPer100g = nutriments.carbohydrates100g.roundToInt(),
		carbsPer100g = nutriments.carbohydrates100g.roundToInt(),
		proteinPer100g = nutriments.proteins100g.roundToInt(),
		fatPer100g = nutriments.fat100g.roundToInt()
	)
}