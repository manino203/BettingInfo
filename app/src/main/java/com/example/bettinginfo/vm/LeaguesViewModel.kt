package com.example.bettinginfo.vm

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.example.bettinginfo.R
import com.example.bettinginfo.data.repository.LeaguesRepository
import com.example.bettinginfo.data.ui_model.League
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.SerializationException
import java.io.FileNotFoundException
import java.io.IOException

@Immutable
data class LeaguesUiState(
    val leagues: List<League> = emptyList(),
    val error: Int? = null
)

class LeaguesViewModel(
    private val repo: LeaguesRepository,
    sportId: Int
): ViewModel() {
    private val _uiState = MutableStateFlow(LeaguesUiState())
    val uiState: StateFlow<LeaguesUiState>
        get() = _uiState.asStateFlow()

    init{
        loadLeagues(sportId)
    }

    private fun loadLeagues(sportId: Int){
        _uiState.update{
            try{
                it.copy(leagues = repo.getLeagues(sportId))
            }catch (e: Exception){
                it.copy(
                    error = when (e){
                        is FileNotFoundException -> R.string.file_not_found_error
                        is IOException -> R.string.reading_error
                        is SerializationException -> R.string.serialization_error
                        else -> R.string.generic_error
                    }
                )
            }
        }
    }
}