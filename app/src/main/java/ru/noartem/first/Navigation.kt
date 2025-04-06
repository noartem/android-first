package ru.noartem.first

sealed class Screen(val route: String) {
    object Catalog : Screen("catalog")

    object ProductDetail : Screen("product_detail/{productId}") {
        fun createRoute(productId: String) = "product_detail/$productId"
    }

    object Cart : Screen("cart")

    object OrderConfirmation : Screen("order_confirmation")
}
