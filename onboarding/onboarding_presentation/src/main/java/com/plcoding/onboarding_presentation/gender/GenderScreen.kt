package com.plcoding.onboarding_presentation.gender

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
import com.plcoding.core.domain.model.Gender
import com.plcoding.core.util.UiEvent
import com.plcoding.core_ui.LocalSpacing
import com.plcoding.onboarding_presentation.R
import com.plcoding.onboarding_presentation.components.ActionButton
import com.plcoding.onboarding_presentation.components.SelectableButton
import kotlinx.coroutines.flow.collect

@Composable
fun GenderScreen(
	onNavigate: (UiEvent.Navigate) -> Unit,
	genderViewModel: GenderViewModel = hiltViewModel()
) {
	val spacing = LocalSpacing.current

	LaunchedEffect(key1 = true) {
		genderViewModel.uiEvent.collect { event ->
			when (event) {
				is UiEvent.Navigate -> onNavigate(event)
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
				text = stringResource(id = R.string.whats_your_gender),
				style = MaterialTheme.typography.h3
			)
			Spacer(modifier = Modifier.height(spacing.spaceMedium))
			Row {
				SelectableButton(
					text = stringResource(id = R.string.male),
					isSelected = genderViewModel.selectedGender == Gender.Male,
					color = MaterialTheme.colors.primaryVariant,
					selectedTextColor = MaterialTheme.colors.onPrimary,
					onClick = {
						genderViewModel.onGenderClick(Gender.Male)
					},
					textStyle = MaterialTheme.typography.button.copy(
						fontWeight = FontWeight.Normal
					)
				)
				Spacer(modifier = Modifier.width(LocalSpacing.current.spaceSmall))
				SelectableButton(
					text = stringResource(id = R.string.female),
					isSelected = genderViewModel.selectedGender == Gender.Female,
					color = MaterialTheme.colors.primaryVariant,
					selectedTextColor = MaterialTheme.colors.onPrimary,
					onClick = {
						genderViewModel.onGenderClick(Gender.Female)
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
				genderViewModel.onNextClick()
			},
			modifier = Modifier.align(Alignment.BottomEnd)
		)
	}
}