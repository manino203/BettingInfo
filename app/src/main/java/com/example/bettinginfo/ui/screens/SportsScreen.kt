package com.example.bettinginfo.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bettinginfo.data.data_source.BettingInfoDataSourceImpl
import com.example.bettinginfo.data.repository.SportsRepositoryImpl
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

@Composable
@Preview(showBackground = true)
private fun SportsScreenPreview(){
    val context = LocalContext.current
    SportsScreen(
        viewModel = SportsViewModel(
            SportsRepositoryImpl(BettingInfoDataSourceImpl(context))
        ),
        contentPadding = PaddingValues()
    ){ _, _ ->
    }
}