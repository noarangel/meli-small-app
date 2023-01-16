package com.smallmeliapp.model

import com.google.gson.annotations.SerializedName

data class CategoryModel(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
)