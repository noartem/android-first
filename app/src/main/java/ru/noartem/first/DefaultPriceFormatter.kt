package ru.noartem.first

import android.annotation.SuppressLint

class DefaultPriceFormatter() : PriceFormatter {
    @SuppressLint("DefaultLocale")
    override fun format(price: Double): String {
        return String.format("%.2f", price)
    }
}