package ru.noartem.first

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.noartem.first.ui.theme.FirstTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    navController: NavController,
    product: Product?,
    priceFormatter: PriceFormatter = DefaultPriceFormatter(),
    addToCart: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(product?.name ?: "Товар не найден") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        },
    ) { innerPadding ->
        if (product == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("Не можем найти такой товар(")
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(product.name, style = MaterialTheme.typography.headlineLarge)
                Spacer(modifier = Modifier.height(8.dp))
                if (product.hasDiscount()) {
                    PriceView(
                        price = product.calcPrice(withDiscount = false),
                        formatter = priceFormatter,
                        modifier = Modifier.align(Alignment.End),
                        color = MaterialTheme.colorScheme.error,
                        textDecoration = TextDecoration.LineThrough
                    )
                    Text(
                        "Скидка: ${product.discountPercent}%",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
                PriceView(
                    price = product.calcPrice(),
                    formatter = priceFormatter,
                    modifier = Modifier.align(Alignment.End)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("Описание", style = MaterialTheme.typography.titleMedium)
                Text(product.description, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = { addToCart() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Добавить в корзину")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailScreenPreview() {
    FirstTheme {
        val navController = NavController(LocalContext.current)
        ProductDetailScreen(
            navController = navController,
            product = sampleProducts.first(),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailScreenNotFoundPreview() {
    FirstTheme {
        val navController = NavController(LocalContext.current)
        ProductDetailScreen(
            navController = navController,
            product = null,
        )
    }
}