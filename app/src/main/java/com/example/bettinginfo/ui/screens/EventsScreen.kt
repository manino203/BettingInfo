package com.example.bettinginfo.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bettinginfo.ui.composables.EventItem
import com.example.bettinginfo.ui.composables.Screen
import com.example.bettinginfo.vm.EventsViewModel

@Composable
fun EventsScreen(
    viewModel: EventsViewModel,
    contentPadding: PaddingValues
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Screen(
        uiState.value.error,
        contentPadding,
        uiState.value.events
    ) {
        EventItem(event = it)
    }
}