package com.example.bettinginfo.data.serialization_model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Offer(
    @SerialName("BetradarTeams")
    val betradarTeams: Map<String, Int>,
    @SerialName("EventChanceTypes")
    val eventChanceTypes: List<EventChanceType>,
    @SerialName("Labels")
    val labels: Map<String, Label>,
    @SerialName("Odds")
    val odds: Map<String,Odd>
)