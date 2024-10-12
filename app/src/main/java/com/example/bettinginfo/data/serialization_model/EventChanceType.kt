package com.example.bettinginfo.data.serialization_model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventChanceType(
    @SerialName("ActualGamePartID")
    val actualGamePartID: String,
    @SerialName("ActualGamePartTime")
    val actualGamePartTime: Int,
    @SerialName("BetType")
    val betType: String,
    @SerialName("ChanceTypeID")
    val chanceTypeID: String,
    @SerialName("ChanceTypeName")
    val chanceTypeName: String,
    @SerialName("EventChanceTypeID")
    val eventChanceTypeID: Int,
    @SerialName("EventChanceTypeStatus")
    val eventChanceTypeStatus: String,
    @SerialName("EventDate")
    val eventDate: String,
    @SerialName("EventID")
    val eventID: Int,
    @SerialName("EventName")
    val eventName: String,
    @SerialName("LeagueCupID")
    val leagueCupID: Int,
    @SerialName("LiveBetting")
    val liveBetting: String,
    @SerialName("LiveBettingView")
    val liveBettingView: Int,
    @SerialName("LiveFeedReference")
    val liveFeedReference: Int,
    @SerialName("RegionID")
    val regionID: Int,
    @SerialName("SideBet")
    val sideBet: Int,
    @SerialName("SportEventID")
    val sportEventID: Int,
    @SerialName("SportID")
    val sportID: Int
)