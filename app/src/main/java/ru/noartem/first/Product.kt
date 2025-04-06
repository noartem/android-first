package ru.noartem.first

class Product(
    val name: String,
    val price: Double,
    val discountPercent: Int = 0,
    val id: String = "",
    val description: String = ""
) {
    fun calcPrice(withDiscount: Boolean = true): Double =
        if (withDiscount)
            price * (1 - discountPercent / 100.0)
        else
            price

    fun calcDiscount(): Double = price * discountPercent / 100

    fun hasDiscount(): Boolean = discountPercent > 0

    fun copy(
        id: String = this.id,
        name: String = this.name,
        description: String = this.description,
        price: Double = this.price,
        discountPercent: Int = this.discountPercent
    ) = Product(
        id = id,
        name = name,
        description = description,
        price = price,
        discountPercent = discountPercent,
    )

}

val sampleProducts = listOf(
    Product(
        id = "a1",
        name = "iPhone XR",
        price = 499.99,
        discountPercent = 10,
        description = "A colorful and powerful smartphone from Apple."
    ),
    Product(
        id = "s1",
        name = "Samsung Galaxy S21",
        price = 699.99,
        discountPercent = 15,
        description = "Flagship Android phone with an amazing camera."
    ),
    Product(
        id = "g1",
        name = "Google Pixel 6",
        price = 599.00,
        description = "Experience the best of Google's software and hardware."
    ),
    Product(
        id = "o1",
        name = "OnePlus 9",
        price = 629.50,
        discountPercent = 5,
        description = "Smooth performance and fast charging."
    )
)
