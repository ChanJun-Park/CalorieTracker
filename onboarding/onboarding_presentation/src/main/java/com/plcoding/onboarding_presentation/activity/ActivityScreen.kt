package com.plcoding.onboarding_presentation.activity

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
import com.plcoding.core.domain.model.ActivityLevel
import com.plcoding.core.util.UiEvent
import com.plcoding.core_ui.LocalSpacing
import com.plcoding.onboarding_presentation.R
import com.plcoding.onboarding_presentation.components.ActionButton
import com.plcoding.onboarding_presentation.components.SelectableButton
import kotlinx.coroutines.flow.collect

@Composable
fun ActivityScreen(
	onNextClick: () -> Unit,
	viewModel: ActivityViewModel = hiltViewModel()
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
				text = stringResource(id = R.string.whats_your_activity_level),
				style = MaterialTheme.typography.h3
			)
			Spacer(modifier = Modifier.height(spacing.spaceMedium))
			Row {
				SelectableButton(
					text = stringResource(id = R.string.low),
					isSelected = viewModel.selectedActivityLevel == ActivityLevel.Low,
					color = MaterialTheme.colors.primaryVariant,
					selectedTextColor = MaterialTheme.colors.onPrimary,
					onClick = {
						viewModel.onActivityLevelSelected(ActivityLevel.Low)
					},
					textStyle = MaterialTheme.typography.button.copy(
						fontWeight = FontWeight.Normal
					)
				)
				Spacer(modifier = Modifier.width(LocalSpacing.current.spaceSmall))
				SelectableButton(
					text = stringResource(id = R.string.medium),
					isSelected = viewModel.selectedActivityLevel == ActivityLevel.Medium,
					color = MaterialTheme.colors.primaryVariant,
					selectedTextColor = MaterialTheme.colors.onPrimary,
					onClick = {
						viewModel.onActivityLevelSelected(ActivityLevel.Medium)
					},
					textStyle = MaterialTheme.typography.button.copy(
						fontWeight = FontWeight.Normal
					)
				)
				Spacer(modifier = Modifier.width(LocalSpacing.current.spaceSmall))
				SelectableButton(
					text = stringResource(id = R.string.high),
					isSelected = viewModel.selectedActivityLevel == ActivityLevel.High,
					color = MaterialTheme.colors.primaryVariant,
					selectedTextColor = MaterialTheme.colors.onPrimary,
					onClick = {
						viewModel.onActivityLevelSelected(ActivityLevel.High)
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