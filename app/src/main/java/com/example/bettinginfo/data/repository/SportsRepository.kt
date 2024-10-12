package com.example.bettinginfo.data.repository

import com.example.bettinginfo.data.data_source.BettingInfoDataSource
import com.example.bettinginfo.data.ui_model.Sport

interface SportsRepository {
    fun getSports(): List<Sport>
}

class SportsRepositoryImpl(
    private val dataSource: BettingInfoDataSource
): SportsRepository{
    override fun getSports(): List<Sport> {
        return dataSource.getOffer().let { offer ->
            offer.labels.values.filter { it.type == "SP" }.map { sportLabel ->
                Sport(
                    sportLabel.name,
                    sportLabel.id
                )
            }
        }
    }
}