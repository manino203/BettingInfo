package com.example.bettinginfo.data.repository

import com.example.bettinginfo.data.data_source.BettingInfoDataSource
import com.example.bettinginfo.data.ui_model.League

interface LeaguesRepository {
    fun getLeagues(sportId: Int): List<League>
}

class LeaguesRepositoryImpl(
    private val dataSource: BettingInfoDataSource
) : LeaguesRepository {
    override fun getLeagues(sportId: Int): List<League> {
        return dataSource.getOffer().let { offer ->
            offer.eventChanceTypes.filter {
                it.sportID == sportId
            }.map {
                it.leagueCupID
            }.distinct().map { leagueId ->
                League(
                    offer.labels.values.find { it.id == leagueId && it.type == "LC" }?.name ?: "",
                    leagueId
                )
            }
        }
    }

}