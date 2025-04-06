package ru.noartem.first

class Product(
    val name: String,
    private val price: Double,
    private val discountPercent: Int = 0
) {
    fun calcPrice(withDiscount: Boolean = true): Double =
        if (withDiscount)
            price * (1 - discountPercent / 100.0)
        else
            price

    fun calcDiscount(): Double = price * discountPercent / 100
}