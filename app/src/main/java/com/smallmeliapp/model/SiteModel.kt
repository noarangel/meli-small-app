package com.smallmeliapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SiteModel(
    @SerializedName("default_currency_id") val defaultCurrencyId: String,
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
) : Parcelable