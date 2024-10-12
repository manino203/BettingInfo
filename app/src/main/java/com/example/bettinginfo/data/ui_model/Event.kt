package com.example.bettinginfo.data.ui_model

data class Event(
    val title: String,
    val region: String,
    val date: String,
    val league: String,
    val sport: String,
    val odds: Odds
)
