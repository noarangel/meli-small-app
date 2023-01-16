package com.smallmeliapp.model

data class CurrencyModel(
    var iso: String?,
    var subunitToUnit: Int,
    var symbol: String?,
    var disambiguateSymbol: String?
)
