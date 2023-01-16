package com.smallmeliapp.model

import com.google.gson.annotations.SerializedName

data class SearchResultItemModel(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("site_id") val siteId: String,
    @SerializedName("category_id") val category_Id: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("currency_id") val currencyId: String,
    @SerializedName("order_backend") val orderBackend: String,
    @SerializedName("price") val price: String?,
    @SerializedName("original_price") val originalPrice: String?,
    @SerializedName("sale_price") val salePrice: String?,
    @SerializedName("official_store_id") val officialStoreId: Int?,
    @SerializedName("shipping") val shippingInfoModel: ShippingInfoModel?,
)
