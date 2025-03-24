package ru.noartem.first

class Presenter(
    private val cart: Cart,
    private val priceFormatter: PriceFormatter = DefaultPriceFormatter()
) {
    fun getTotalPrice() = priceFormatter.format(cart.calcTotalPrice())

    fun print() {
        if (cart.products.isEmpty()) {
            println("Cart is empty")
        } else {
            println("Cart products:")
            cart.products.forEach { product ->
                println("\t${product.name}: ${priceFormatter.format(product.calcDiscountPrice())}")
            }
            println("Total cost: ${priceFormatter.format(cart.calcTotalPrice())}")
        }
    }
}