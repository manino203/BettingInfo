package com.example.bettinginfo.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bettinginfo.ui.composables.CategoryItem
import com.example.bettinginfo.ui.composables.Screen
import com.example.bettinginfo.vm.LeaguesViewModel


@Composable
fun LeaguesScreen(
    viewModel: LeaguesViewModel,
    contentPadding: PaddingValues,
    onLeagueClick: (Int, String) -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Screen(
        uiState.value.error,
        contentPadding,
        uiState.value.leagues
    ) {
        CategoryItem(label = it.label) {
            onLeagueClick(it.id, it.label)
        }
    }
}