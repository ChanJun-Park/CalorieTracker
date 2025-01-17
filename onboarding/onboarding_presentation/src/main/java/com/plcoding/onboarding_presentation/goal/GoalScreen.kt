package com.plcoding.onboarding_presentation.goal

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.plcoding.core.domain.model.GoalType
import com.plcoding.core.util.UiEvent
import com.plcoding.core_ui.LocalSpacing
import com.plcoding.onboarding_presentation.R
import com.plcoding.onboarding_presentation.components.ActionButton
import com.plcoding.onboarding_presentation.components.SelectableButton
import kotlinx.coroutines.flow.collect

@Composable
fun GoalScreen(
	onNextClick: () -> Unit,
	viewModel: GoalViewModel = hiltViewModel()
) {
	val spacing = LocalSpacing.current

	LaunchedEffect(key1 = true) {
		viewModel.uiEvent.collect { event ->
			when (event) {
				is UiEvent.Success -> onNextClick()
				else -> Unit
			}
		}
	}

	Box(
		modifier = Modifier
			.fillMaxSize()
			.padding(spacing.spaceLarge)
	) {

		Column(
			modifier = Modifier
				.fillMaxSize(),
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Text(
				text = stringResource(id = R.string.lose_keep_or_gain_weight),
				style = MaterialTheme.typography.h3
			)
			Spacer(modifier = Modifier.height(spacing.spaceMedium))
			Row {
				SelectableButton(
					text = stringResource(id = R.string.lose),
					isSelected = viewModel.selectedGoalType == GoalType.LoseWeight,
					color = MaterialTheme.colors.primaryVariant,
					selectedTextColor = MaterialTheme.colors.onPrimary,
					onClick = {
						viewModel.onGoalTypeSelected(GoalType.LoseWeight)
					},
					textStyle = MaterialTheme.typography.button.copy(
						fontWeight = FontWeight.Normal
					)
				)
				Spacer(modifier = Modifier.width(LocalSpacing.current.spaceSmall))
				SelectableButton(
					text = stringResource(id = R.string.keep),
					isSelected = viewModel.selectedGoalType == GoalType.KeepWeight,
					color = MaterialTheme.colors.primaryVariant,
					selectedTextColor = MaterialTheme.colors.onPrimary,
					onClick = {
						viewModel.onGoalTypeSelected(GoalType.KeepWeight)
					},
					textStyle = MaterialTheme.typography.button.copy(
						fontWeight = FontWeight.Normal
					)
				)
				Spacer(modifier = Modifier.width(LocalSpacing.current.spaceSmall))
				SelectableButton(
					text = stringResource(id = R.string.gain),
					isSelected = viewModel.selectedGoalType == GoalType.GainWeight,
					color = MaterialTheme.colors.primaryVariant,
					selectedTextColor = MaterialTheme.colors.onPrimary,
					onClick = {
						viewModel.onGoalTypeSelected(GoalType.GainWeight)
					},
					textStyle = MaterialTheme.typography.button.copy(
						fontWeight = FontWeight.Normal
					)
				)
			}
		}

		ActionButton(
			text = stringResource(id = R.string.next),
			onClick = {
				viewModel.onNextClick()
			},
			modifier = Modifier.align(Alignment.BottomEnd)
		)
	}
}