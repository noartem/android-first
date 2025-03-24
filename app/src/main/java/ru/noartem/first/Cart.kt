package ru.noartem.first

class Cart(private val products: List<Product> = listOf()) {
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

    fun print(priceFormatter: PriceFormatter = DefaultPriceFormatter()) {
        if (products.isEmpty()) {
            println("Cart is empty")
        } else {
            println("Cart products:")
            products.forEach { product ->
                println("\t${product.name}: ${priceFormatter.format(product.calcDiscountPrice())}")
            }
            println("Total cost: ${priceFormatter.format(calcTotalPrice())}")
        }
    }
}