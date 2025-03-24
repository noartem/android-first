package ru.noartem.first

import android.icu.util.ULocale
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.noartem.first.ui.theme.FirstTheme

class MainActivity : ComponentActivity() {
    private val cart = Cart(
        listOf(
            Product(name = "iPhone XR", price = 499.99, discountPercent = 10),
            Product(name = "iPhone XS", price = 599.99, discountPercent = 15),
            Product(name = "iPhone 11", price = 699.99, discountPercent = 12),
            Product(name = "iPhone 12", price = 799.99, discountPercent = 8),
            Product(name = "iPhone 13", price = 899.99, discountPercent = 5),
            Product(name = "iPhone 14", price = 999.99, discountPercent = 3)
        )
    )

    private val presenter = Presenter(
        cart = cart,
        priceFormatter = LocalePriceFormatter(),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirstTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PresenterView(
                        presenter = presenter,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PresenterView(
    presenter: Presenter,
    modifier: Modifier = Modifier,
) {
    Text(
        text = presenter.getTotalPrice(),
        modifier = modifier,
    )
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
fun GreetingPreview() {
    FirstTheme {
        Column {
            PriceView(115.0)
            PriceView(115.0, formatter = LocalePriceFormatter())
            PriceView(115.0, formatter = LocalePriceFormatter(ULocale.JAPAN))
            PriceView(115.0, formatter = LocalePriceFormatter(ULocale.US))
        }
    }
}