package ru.noartem.first

class Cart(val products: List<Product> = listOf()) {
    fun calcTotalPrice(discount: Boolean = true): Double =
        products.sumOf { it.calcPrice(discount) }

    fun calcTotalDiscount(): Double =
        products.sumOf { it.calcDiscount() }

    fun addProduct(product: Product): Cart {
        return Cart(products + product)
    }

    fun removeProduct(product: Product): Cart {
        return Cart(products - product)
    }

    fun removeProduct(i: Int): Cart {
        return Cart(products - products[i])
    }
}