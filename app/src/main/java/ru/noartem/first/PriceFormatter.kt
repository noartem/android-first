package ru.noartem.first

interface PriceFormatter {
    val locale: String

    fun format(price: Double): String {
        return "$price"
    }


    fun fact() {
        println("wow")
    }
}