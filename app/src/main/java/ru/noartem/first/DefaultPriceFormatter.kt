package ru.noartem.first

import android.annotation.SuppressLint
import android.icu.text.NumberFormat
import android.icu.util.ULocale

class DefaultPriceFormatter() : PriceFormatter {
    @SuppressLint("DefaultLocale")
    override fun format(price: Double): String {
        return String.format("%.2f", price)
    }
}