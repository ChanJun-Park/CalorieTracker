package com.plcoding.tracker_presentation.tracker_overview.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.plcoding.core.R
import com.plcoding.core_ui.LocalSpacing
import com.plcoding.tracker_presentation.components.NutrientInfo
import com.plcoding.tracker_presentation.tracker_overview.Meal

@Composable
fun ExpandableMeal(
	meal: Meal,
	onToggleClick: () -> Unit,
	content: @Composable () -> Unit,
	modifier: Modifier
) {
	val spacing = LocalSpacing.current
	val context = LocalContext.current

	Column(
		modifier = modifier
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.clickable { onToggleClick() }
				.padding(spacing.spaceMedium),
			verticalAlignment = Alignment.CenterVertically
		) {
			Image(
				painter = painterResource(id = meal.drawableRes),
				contentDescription = meal.name.asString(context)
			)
			Spacer(modifier = Modifier.width(spacing.spaceMedium))
			Column(
				modifier = Modifier.weight(1f)
			) {
				Row(
					modifier = Modifier.fillMaxWidth(),
					horizontalArrangement = Arrangement.SpaceBetween
				) {
					Text(
						text = meal.name.asString(context),
						style = MaterialTheme.typography.h3
					)
					Image(
						imageVector = if (meal.isExpanded) {
							Icons.Default.KeyboardArrowUp
						} else {
							Icons.Default.KeyboardArrowDown
						},
						contentDescription = if (meal.isExpanded) {
							stringResource(id = R.string.collapse)
						} else {
							stringResource(id = R.string.extend)
						}
					)
				}
				Spacer(modifier = Modifier.height(spacing.spaceSmall))
				Row(
					modifier = Modifier.fillMaxWidth(),
					horizontalArrangement = Arrangement.SpaceBetween
				) {
					NutrientInfo(
						amount = meal.carbs,
						unit = stringResource(id = R.string.grams),
						name = stringResource(id = R.string.carbs)
					)
					Spacer(modifier = Modifier.width(spacing.spaceSmall))
					NutrientInfo(
						amount = meal.protein,
						unit = stringResource(id = R.string.grams),
						name = stringResource(id = R.string.protein)
					)
					Spacer(modifier = Modifier.width(spacing.spaceSmall))
					NutrientInfo(
						amount = meal.fat,
						unit = stringResource(id = R.string.grams),
						name = stringResource(id = R.string.fat)
					)
				}
			}
		}
		Spacer(modifier = Modifier.height(spacing.spaceMedium))
		AnimatedVisibility(visible = meal.isExpanded) {
			content()
		}
	}
}