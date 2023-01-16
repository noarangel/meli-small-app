package com.smallmeliapp.model

import com.google.gson.annotations.SerializedName

data class ProductItemDetailModel(
    @SerializedName("id") val id: String,
    @SerializedName("site_id") val siteId: String,
    @SerializedName("title") val title: String,
    @SerializedName("subtitle") val subtitle: String?,
    @SerializedName("seller_id") val sellerId: Int?,
    @SerializedName("category_id") val category_Id: String,
    @SerializedName("official_store_id") val officialStoreId: Int?,
    @SerializedName("price") val price: String,
    @SerializedName("base_price") val basePrice: String?,
    @SerializedName("original_price") val originalPrice: String?,
    @SerializedName("currency_id") val currencyId: String,
    @SerializedName("pictures") val pictures: List<ProductPictureModel>,
    @SerializedName("accepts_mercadopago") val acceptsMercadopago: Boolean,
    @SerializedName("shipping") val shippingInfoModel: ShippingInfoModel,
    @SerializedName("attributes") val attributes: List<ProductAttributeModel>?,
    @SerializedName("warranty") val warranty: String?,
)
