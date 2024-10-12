package com.example.bettinginfo.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bettinginfo.data.data_source.BettingInfoDataSourceImpl
import com.example.bettinginfo.data.repository.EventsRepositoryImpl
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

@Composable
@Preview(showBackground = true)
private fun EventsScreenPreview(){
    val context = LocalContext.current
    EventsScreen(
        viewModel = EventsViewModel(
            EventsRepositoryImpl(BettingInfoDataSourceImpl(context)),
            587331
        ),
        contentPadding = PaddingValues()
    )
}