package com.thiru.telstratest.model

import com.google.gson.annotations.SerializedName

data class Row(
        @SerializedName("title") val title: String = "",
        @SerializedName("description") val description: String = "",
        @SerializedName("imageHref") val imageHref: String = ""
)