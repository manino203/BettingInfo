package com.example.bettinginfo.data.repository

import com.example.bettinginfo.data.data_source.BettingInfoDataSource
import com.example.bettinginfo.data.serialization_model.EventChanceType
import com.example.bettinginfo.data.serialization_model.Odd
import com.example.bettinginfo.data.serialization_model.Offer
import com.example.bettinginfo.data.ui_model.Event
import com.example.bettinginfo.data.ui_model.Odds
import java.text.SimpleDateFormat
import java.util.Locale

interface EventsRepository {
    fun getEvents(leagueId: Int): List<Event>
}

class EventsRepositoryImpl(
    private val dataSource: BettingInfoDataSource
): EventsRepository{
    override fun getEvents(leagueId: Int): List<Event> {
        return dataSource.getOffer().let{ offer ->
            offer.eventChanceTypes.filter{ it.leagueCupID == leagueId }.map{
                it.toEvent(offer)
            }
        }
    }

    private fun EventChanceType.toEvent(offer: Offer): Event =
        Event(
            this.eventName,
            offer.labels.values.find { it.id == this.regionID && it.type == "RE" }?.name ?: "",
            reformatDate(this.eventDate),
            offer.labels.values.find { it.id == this.leagueCupID && it.type == "LC" }?.name ?: "",
            offer.labels.values.find { it.id == this.sportID && it.type == "SP" }?.name ?: "",
            offer.odds.values.filter { odd ->
                odd.eventChanceTypeID == this.eventChanceTypeID
            }.toOdds()
        )


    private fun List<Odd>.toOdds(): Odds =
        Odds(
            odd1 = this.find { it.tipType == "1" }?.oddsRate,
            oddX = this.find { it.tipType == "X" }?.oddsRate,
            odd2 = this.find { it.tipType == "2" }?.oddsRate,
            oddX1 = this.find { it.tipType == "1X" }?.oddsRate,
            oddX2 = this.find { it.tipType == "X2" }?.oddsRate,
            odd12 = this.find { it.tipType == "12" }?.oddsRate,
        )

    private fun reformatDate(inputDate: String): String =
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS", Locale.getDefault()).parse(inputDate)
            ?.let { SimpleDateFormat("d.M HH:mm", Locale.getDefault()).format(it) } ?: "1.1 00:00"

}