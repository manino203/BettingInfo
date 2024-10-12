package com.example.bettinginfo.vm

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.example.bettinginfo.R
import com.example.bettinginfo.data.repository.EventsRepository
import com.example.bettinginfo.data.ui_model.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.SerializationException
import java.io.FileNotFoundException
import java.io.IOException

@Immutable
data class EventsUiState(
    val events: List<Event> = emptyList(),
    val error: Int? = null
)

class EventsViewModel(
    private val repo: EventsRepository,
    leagueId: Int
): ViewModel() {
    private val _uiState = MutableStateFlow(EventsUiState())
    val uiState: StateFlow<EventsUiState>
        get() = _uiState.asStateFlow()

    init{
        loadEvents(leagueId)
    }

    private fun loadEvents(leagueId: Int){
        _uiState.update{
            try{
                it.copy(events = repo.getEvents(leagueId))
            }catch (e: Exception){
                Log.d("events", e.message ?: "")
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