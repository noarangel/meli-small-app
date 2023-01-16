package com.smallmeliapp.core.extension

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.smallmeliapp.R
import com.smallmeliapp.core.api.exception.ApiException
import com.smallmeliapp.utils.Constants

object StringExtension {

    private val sitesImageMap: HashMap<String, Int> = hashMapOf<String, Int>(
        Constants.ID_SITE_CHILE to R.drawable.ic_round_chile,
        Constants.ID_SITE_ARGENTINA to R.drawable.ic_round_argentina,
        Constants.ID_SITE_CUBA to R.drawable.ic_round_cuba,
        Constants.ID_SITE_BRASIL to R.drawable.ic_round_brasil,
        Constants.ID_SITE_BOLIVIA to R.drawable.ic_round_bolivia,
        Constants.ID_SITE_COSTA_RICA to R.drawable.ic_round_costa_rica,
        Constants.ID_SITE_DOMINICANA to R.drawable.ic_round_republica_dominicana,
        Constants.ID_SITE_ECUADOR to R.drawable.ic_round_ecuador,
        Constants.ID_SITE_EL_SALVADOR to R.drawable.ic_round_el_salvador,
        Constants.ID_SITE_GUATEMALA to R.drawable.ic_round_guatemala,
        Constants.ID_SITE_HONDURAS to R.drawable.ic_round_honduras,
        Constants.ID_SITE_MEXICO to R.drawable.ic_round_mexico,
        Constants.ID_SITE_NICARAGUA to R.drawable.ic_round_nicaragua,
        Constants.ID_SITE_PANAMA to R.drawable.ic_round_panama,
        Constants.ID_SITE_PARAGUAY to R.drawable.ic_round_paraguay,
        Constants.ID_SITE_URUGUAY to R.drawable.ic_round_uruguay,
        Constants.ID_SITE_VENEZUELA to R.drawable.ic_round_venezuela,
        Constants.ID_SITE_PERU to R.drawable.ic_round_peru,
        Constants.ID_SITE_COLOMBIA to R.drawable.ic_round_colombia
    )


    fun String.getCountryIcon(): Int {
        return sitesImageMap[this] ?: R.drawable.ic_round_colombia
    }

    internal fun String.toJson(): JsonObject = JsonParser()
        .parse(this)
        .asJsonObject

    internal fun String.isJson() = try {
        val gson = Gson()
        gson.fromJson(this, Any::class.java)
        true
    } catch (ex: com.google.gson.JsonSyntaxException) {
        false
    }

    internal fun String.toApiException() =
        ApiException(this)

}