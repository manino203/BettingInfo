package com.example.bettinginfo.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class TopBarUiState(
    val title: String? = null
)

class TopBarViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(TopBarUiState())
    val uiState: StateFlow<TopBarUiState>
        get() = _uiState.asStateFlow()

    fun updateTitle(value: String?){
        _uiState.update { it.copy(title = value) }
    }
}