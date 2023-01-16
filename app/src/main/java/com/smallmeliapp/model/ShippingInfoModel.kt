package com.smallmeliapp.model

import com.google.gson.annotations.SerializedName

data class ShippingInfoModel(
    @SerializedName("logistic_type") val logisticType: String,
    @SerializedName("mode") val mode: String,
    @SerializedName("store_pick_up") val storePickUp: Boolean,
    @SerializedName("free_shipping") val freeShipping: Boolean,
)
