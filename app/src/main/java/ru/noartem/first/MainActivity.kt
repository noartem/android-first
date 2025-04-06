package ru.noartem.first

import android.icu.util.ULocale
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.NavController
import androidx.navigation.compose.composable
import ru.noartem.first.ui.theme.FirstTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val cart = Cart(
                listOf(
                    Product(name = "iPhone XR", price = 499.99, discountPercent = 10),
                )
            )

            FirstTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(
                        cart = cart,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun App(
    cart: Cart,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(
                cart = cart,
                navController = navController,
                modifier = modifier
            )
        }
        composable("order-confirmation") {
            OrderConfirmationScreen(cart = cart, modifier = modifier)
        }
    }
}

@Composable
fun MainScreen(
    cart: Cart,
    modifier: Modifier = Modifier,
    navController: NavController? = null,
) {
    Column(modifier) {
        PriceView(
            price = cart.calcTotalPrice(),
            formatter = LocalePriceFormatter(),
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = { navController?.navigate("order-confirmation") }) {
            Text("К оформлению заказа")
        }
    }
}

@Composable
fun PriceView(
    price: Double,
    modifier: Modifier = Modifier,
    formatter: PriceFormatter = DefaultPriceFormatter()
) {
    Text(
        text = formatter.format(price),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    FirstTheme {
        Box(modifier = Modifier.height(400.dp)) {
            MainScreen(
                cart = Cart(
                    listOf(
                        Product(name = "iPhone XR", price = 499.99, discountPercent = 10),
                    )
                ),
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PriceViewPreview() {
    FirstTheme {
        Column {
            PriceView(115.0)
            PriceView(115.0, formatter = LocalePriceFormatter())
            PriceView(115.0, formatter = LocalePriceFormatter(ULocale.JAPAN))
            PriceView(115.0, formatter = LocalePriceFormatter(ULocale.US))
        }
    }
}