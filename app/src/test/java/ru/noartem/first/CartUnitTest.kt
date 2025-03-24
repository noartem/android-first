package ru.noartem.first

import org.junit.Test

import org.junit.Assert.*

class CartUnitTest {
    @Test
    fun mutableCart_print() {
        val cart = MutableCart()
        cart.addProduct(Product("Product 1", 100.0, 10))
        cart.addProduct(Product("Product 2", 200.0, 20))
        cart.addProduct(Product("Product 3", 300.0, 30))
        cart.removeProduct(2)
        cart.print()
    }

    @Test
    fun cart_print() {
        val cart = Cart()
            .addProduct(Product("Product 1", 100.0, 10))
            .addProduct(Product("Product 2", 200.0, 20))
            .addProduct(Product("Product 3", 300.0, 30))
            .removeProduct(2)
        cart.print()
    }
}