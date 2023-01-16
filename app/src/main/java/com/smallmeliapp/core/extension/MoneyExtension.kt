package com.smallmeliapp.core.extension

import com.smallmeliapp.model.CurrencyModel
import com.smallmeliapp.utils.Constants
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

object MoneyExtension {

    private val currencies: HashMap<String, CurrencyModel?> = hashMapOf<String, CurrencyModel?>(
        Constants.ID_SITE_CHILE to CurrencyModel("CLP", 1, "$", "CLP"),
        Constants.ID_SITE_ARGENTINA to CurrencyModel("ARS", 100, "$", "ARS$"),
        Constants.ID_SITE_CUBA to CurrencyModel("CUC", 100, "$", "CUC"),
        Constants.ID_SITE_BRASIL to CurrencyModel("BRL", 100, "R$", "R$"),
        Constants.ID_SITE_BOLIVIA to CurrencyModel("BOB", 100, "Bs", "Bs"),
        Constants.ID_SITE_COSTA_RICA to CurrencyModel("CRC", 100, "₡", "CRC"),
        Constants.ID_SITE_DOMINICANA to CurrencyModel("DOP", 100, "RD$", "DOP"),
        Constants.ID_SITE_ECUADOR to CurrencyModel("ECS", 100, "S/", "ECS"),
        Constants.ID_SITE_EL_SALVADOR to CurrencyModel("SVC", 100, "₡", "SVC"),
        Constants.ID_SITE_GUATEMALA to CurrencyModel("GTQ", 100, "Q", "Q"),
        Constants.ID_SITE_HONDURAS to CurrencyModel("HNL", 100, "L", "HNL"),
        Constants.ID_SITE_MEXICO to CurrencyModel("MXN", 100, "$", "MEX$"),
        Constants.ID_SITE_NICARAGUA to CurrencyModel("NIO", 100, "C$", "NIO"),
        Constants.ID_SITE_PANAMA to CurrencyModel("PAB", 100, "B/", "PAB"),
        Constants.ID_SITE_PARAGUAY to CurrencyModel("PYG", 1, "₲", "₲"),
        Constants.ID_SITE_URUGUAY to CurrencyModel("UYU", 100, "$", "$"),
        Constants.ID_SITE_VENEZUELA to CurrencyModel("VES", 100, "Bs", "VES"),
        Constants.ID_SITE_PERU to CurrencyModel("PEN", 100, "S/", "S/"),
        Constants.ID_SITE_COLOMBIA to CurrencyModel("COP", 100, "$", "COL$")
    )


    fun String.geSimpleFormat(siteId: String): String {
        val currency: CurrencyModel? = currencies[siteId]
        val formatter =
            DecimalFormat(currency?.symbol + " " + "#,###", DecimalFormatSymbols(Locale.US))
        return formatter.format(this.toDouble())
    }

}
