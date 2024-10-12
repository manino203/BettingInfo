package com.example.bettinginfo.data.data_source

import android.content.Context
import com.example.bettinginfo.data.serialization_model.Offer
import kotlinx.serialization.json.Json

interface BettingInfoDataSource {
    fun getOffer(): Offer
}

class BettingInfoDataSourceImpl(private val context: Context): BettingInfoDataSource{
    private var offer: Offer? = null
    override fun getOffer(): Offer {
        return offer ?: context.assets.open("offer.json")
            .bufferedReader()
            .use { it.readText() }
            .let { json ->
                Json.decodeFromString<Offer>(json).let{
                    offer = it
                    it
                }
            }
    }
}