package ru.noartem.first

class Product(
    private val price: Double,
    private val discountPercent: Int = 0
) {
    fun calcDiscountPrice(): Double = price * (1 - discountPercent / 100.0)
}