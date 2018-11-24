package com.thiru.telstratest.model

import com.google.gson.annotations.SerializedName

data class CountryData(
        @SerializedName("title") val title: String = "",
        @SerializedName("rows") val rows: MutableList<Row> = mutableListOf()
)