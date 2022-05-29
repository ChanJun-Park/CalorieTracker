package com.plcoding.tracker_presentation.tracker_overview

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.plcoding.core.util.UiEvent
import com.plcoding.core_ui.LocalSpacing
import com.plcoding.tracker_presentation.tracker_overview.components.DaySelector
import com.plcoding.tracker_presentation.tracker_overview.components.NutrientsHeader

@Composable
fun TrackerOverviewScreen(
	onNavigate: (UiEvent.Navigate) -> Unit,
	viewModel: TrackerOverviewViewModel = hiltViewModel()
) {
	val spacing = LocalSpacing.current
	val state = viewModel.state
	val context = LocalContext.current
	LazyColumn(
		modifier = Modifier
			.fillMaxSize()
			.padding(bottom = spacing.spaceMedium)
	) {
		item {
			NutrientsHeader(state = state)
			Spacer(modifier = Modifier.height(spacing.spaceMedium))
			DaySelector(
				date = state.date,
				onPreviousClick = { viewModel.onEvent(TrackerOverviewEvent.OnPreviousDayClick) },
				onNextClick = { viewModel.onEvent(TrackerOverviewEvent.OnNextDayClick) },
				modifier = Modifier
					.fillMaxWidth()
					.padding(horizontal = spacing.spaceMedium)
			)
			Spacer(modifier = Modifier.height(spacing.spaceMedium))
		}
	}

}