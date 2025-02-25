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
    private val iphoneCase = Product(price = 123.5, discountPercent = 50)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirstTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PriceViewer(
                        price = iphoneCase.calcDiscountPrice(),
                        formatter = LocalePriceFormatter(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PriceViewer(
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
            PriceViewer(115.0)
            PriceViewer(115.0, formatter = LocalePriceFormatter())
            PriceViewer(115.0, formatter = LocalePriceFormatter(ULocale.US))
        }
    }
}