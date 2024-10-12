package com.example.bettinginfo.ui.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed interface ScreenDestination: Parcelable {

    @Parcelize
    data object Sports: ScreenDestination

    @Parcelize
    data class Leagues(val sportId: Int, val newTitle: String): ScreenDestination

    @Parcelize
    data class Events(val leagueId: Int, val newTitle: String): ScreenDestination

}