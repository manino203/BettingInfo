package com.example.bettinginfo.data.serialization_model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Odd(
    @SerialName("CompetitorID")
    val competitorID: Int,
    @SerialName("EventChanceTypeID")
    val eventChanceTypeID: Int,
    @SerialName("OddsID")
    val oddsID: Int,
    @SerialName("OddsRate")
    val oddsRate: Double,
    @SerialName("PlayerID")
    val playerID: Int,
    @SerialName("TipID")
    val tipID: String,
    @SerialName("TipOrder")
    val tipOrder: String,
    @SerialName("TipType")
    val tipType: String
)