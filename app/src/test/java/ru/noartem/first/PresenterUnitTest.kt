package ru.noartem.first

import org.junit.Test

class PresenterUnitTest {
    @Test
    fun print() {
        val cart = Cart()
            .addProduct(Product("Product 1", 100.0, 10))
            .addProduct(Product("Product 2", 200.0, 20))
        val presenter = Presenter(
            cart = cart,
            priceFormatter = DefaultPriceFormatter(),
        )
        presenter.print()
    }
}