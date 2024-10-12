package com.example.bettinginfo.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bettinginfo.data.data_source.BettingInfoDataSourceImpl
import com.example.bettinginfo.data.repository.LeaguesRepositoryImpl
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


@Composable
@Preview(showBackground = true)
private fun LeaguesScreenPreview(){
    val context = LocalContext.current
    LeaguesScreen(
        viewModel = LeaguesViewModel(
            LeaguesRepositoryImpl(BettingInfoDataSourceImpl(context)),
            54
        ),
        contentPadding = PaddingValues()
    ){ _, _ ->
    }
}