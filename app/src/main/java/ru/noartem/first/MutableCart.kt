package ru.noartem.first

class MutableCart(private val products: MutableList<Product> = mutableListOf()) {
    fun calcTotalPrice(): Double = products.sumOf { it.calcDiscountPrice() }

    fun addProduct(product: Product) {
        products.add(product)
    }

    fun removeProduct(product: Product) {
        products.remove(product)
    }

    fun removeProduct(i: Int) {
        products.removeAt(i)
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