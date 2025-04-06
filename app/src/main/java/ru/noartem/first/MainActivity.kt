package ru.noartem.first

import android.icu.util.ULocale
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.noartem.first.ui.theme.FirstTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var cart = Cart()

            FirstTheme {
                AppNavigation(
                    cart = cart,
                    products = sampleProducts,
                    priceFormatter = LocalePriceFormatter(ULocale.US),
                    addToCart = { cart = cart.addProduct(it) },
                    removeFromCart = { cart = cart.removeProduct(it) }
                )
            }
        }
    }
}

@Composable
fun AppNavigation(
    cart: Cart,
    products: List<Product>,
    priceFormatter: PriceFormatter,
    addToCart: (Product) -> Unit,
    removeFromCart: (Product) -> Unit = {}
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Catalog.route) {
        composable(Screen.Catalog.route) {
            CatalogScreen(
                navController = navController,
                products = products
            )
        }

        composable(
            route = Screen.ProductDetail.route,
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            val product = products.find { it.id == productId }
            ProductDetailScreen(
                navController = navController,
                product = product,
                addToCart = {
                    if (product != null) {
                        addToCart(product)
                    }
                }
            )
        }

        composable(Screen.Cart.route) {
            CartScreen(
                navController = navController,
                cart = cart,
                priceFormatter = priceFormatter,
                removeFromCart = { removeFromCart(it) }
            )
        }

        composable(Screen.OrderConfirmation.route) {
            OrderConfirmationScreen(
                navController = navController,
                cart = cart,
                priceFormatter = priceFormatter,
            )
        }
    }
}
