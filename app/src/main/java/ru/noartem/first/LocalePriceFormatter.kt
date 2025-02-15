package ru.noartem.first

import android.icu.text.NumberFormat
import android.icu.util.ULocale

class LocalePriceFormatter(private val locale: ULocale = ULocale.getDefault()) : PriceFormatter {
    override fun format(price: Double): String {
        val currencyFormatter = NumberFormat.getCurrencyInstance(locale)
        return currencyFormatter.format(price)
    }
}