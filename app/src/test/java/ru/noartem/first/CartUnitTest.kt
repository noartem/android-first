package ru.noartem.first

import org.junit.Test

import org.junit.Assert.*

class CartUnitTest {
    @Test
    fun cart_print() {
        val cart = Cart()
            .addProduct(Product("Product 1", 100.0, 10))
            .addProduct(Product("Product 2", 200.0, 20))
            .addProduct(Product("Product 3", 300.0, 30))
            .removeProduct(2)
        assertEquals(250.0, cart.calcTotalPrice(), 0.00001)
    }
}