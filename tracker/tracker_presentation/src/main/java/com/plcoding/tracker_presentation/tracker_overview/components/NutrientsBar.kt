package com.plcoding.tracker_presentation.tracker_overview.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plcoding.core_ui.CarbColor
import com.plcoding.core_ui.FatColor
import com.plcoding.core_ui.ProteinColor

@Composable
fun NutrientsBar(
	carbs: Int,
	protein: Int,
	fat: Int,
	calories: Int,
	caloriesGoal: Int,
	modifier: Modifier = Modifier
) {
	val background = MaterialTheme.colors.background
	val caloriesExceedColor = MaterialTheme.colors.error

	val carbsRatio = remember {
		Animatable(0f)
	}
	val proteinRatio = remember {
		Animatable(0f)
	}
	val fatRatio = remember {
		Animatable(0f)
	}

	LaunchedEffect(key1 = carbs) {
		carbsRatio.animateTo(
			targetValue = (carbs * 4f) / caloriesGoal
		)
	}
	LaunchedEffect(key1 = protein) {
		proteinRatio.animateTo(
			targetValue = (protein * 4f) / caloriesGoal
		)
	}
	LaunchedEffect(key1 = fat) {
		fatRatio.animateTo(
			targetValue = (fat * 9f) / caloriesGoal
		)
	}

	Canvas(modifier) {
		drawRoundRect(
			color = if (calories <= caloriesGoal) {
				background
			} else {
				caloriesExceedColor
			},
			size = size,
			cornerRadius = CornerRadius(100f)
		)
		if (calories <= caloriesGoal) {
			val carbsWidth = carbsRatio.value * size.width
			val proteinWidth = proteinRatio.value * size.width
			val fatWidth = fatRatio.value * size.width

			drawRoundRect(
				color = FatColor,
				size = Size(
					width = carbsWidth + proteinWidth + fatWidth,
					height = size.height
				),
				cornerRadius = CornerRadius(100f)
			)
			drawRoundRect(
				color = ProteinColor,
				size = Size(
					width = carbsWidth + proteinWidth,
					height = size.height
				),
				cornerRadius = CornerRadius(100f)
			)
			drawRoundRect(
				color = CarbColor,
				size = Size(
					width = carbsWidth,
					height = size.height
				),
				cornerRadius = CornerRadius(100f)
			)
		}
	}
}

@Preview(widthDp = 360, heightDp = 720)
@Composable
fun NutrientsBarPreview() {
	Column(modifier = Modifier.fillMaxSize()) {
		NutrientsBar(
			carbs = 10,
			protein = 10,
			fat = 10,
			calories = 170,
			caloriesGoal = 180,
			modifier = Modifier
				.height(10.dp)
				.fillMaxWidth()
		)
	}
}