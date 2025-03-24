package ru.noartem.first

class Cart(val products: List<Product> = listOf()) {
    fun calcTotalPrice(): Double = products.sumOf { it.calcDiscountPrice() }

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