package com.smallmeliapp.core.api.exception

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelException(
    @SerializedName("error") @Expose var error: String = "",
    @SerializedName("message") @Expose var mssg: String = ""
) : Parcelable