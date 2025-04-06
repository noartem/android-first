package ru.noartem.first

class Cart(val products: MutableList<Product> = mutableListOf()) {
    fun calcTotalPrice(discount: Boolean = true): Double =
        products.sumOf { it.calcPrice(discount) }

    fun calcTotalDiscount(): Double =
        products.sumOf { it.calcDiscount() }

    fun addProduct(product: Product): Cart {
        if (!products.contains(product)) {
            products.add(product)
        }

        return this
    }

    fun removeProduct(product: Product): Cart {
        if (products.contains(product)) {
            products.remove(product)
        }

        return this
    }

    fun removeProduct(i: Int): Cart {
        if (i >= 0 && i < products.size) {
            products.removeAt(i)
        }

        return this
    }
}