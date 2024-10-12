package com.example.bettinginfo.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bettinginfo.ui.composables.CategoryItem
import com.example.bettinginfo.ui.composables.Screen
import com.example.bettinginfo.vm.SportsViewModel

@Composable
fun SportsScreen(
    viewModel: SportsViewModel,
    contentPadding: PaddingValues,
    onSportClick: (Int, String) -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Screen(
        uiState.value.error,
        contentPadding,
        uiState.value.sports,
    ) {
        CategoryItem(label = it.label) {
            onSportClick(it.id, it.label)
        }
    }
}