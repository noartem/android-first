package ru.noartem.first

interface PriceFormatter {
    fun format(price: Double): String {
        return price.toString()
    }
}