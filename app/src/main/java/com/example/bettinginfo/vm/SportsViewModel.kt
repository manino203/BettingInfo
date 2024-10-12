package com.example.bettinginfo.vm

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.example.bettinginfo.R
import com.example.bettinginfo.data.repository.SportsRepository
import com.example.bettinginfo.data.ui_model.Sport
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.SerializationException
import java.io.FileNotFoundException
import java.io.IOException

@Immutable
data class MainUiState(
    val sports: List<Sport> = emptyList(),
    val error: Int? = null
)

class SportsViewModel(
    private val repo: SportsRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState>
        get() = _uiState.asStateFlow()

    init{
        loadSports()
    }

    private fun loadSports(){
        _uiState.update{
            try{
                it.copy(sports = repo.getSports())
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