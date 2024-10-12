package com.example.bettinginfo.data.serialization_model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Label(
    @SerialName("ID")
    val id: Int,
    @SerialName("LanguageID")
    val languageID: String,
    @SerialName("Name")
    val name: String,
    @SerialName("Typ")
    val type: String
)