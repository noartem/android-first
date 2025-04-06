package ru.noartem.first

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
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
fun CatalogScreen(navController: NavController, products: List<Product>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Каталог") },
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.Cart.route) }) {
                        Icon(Icons.Filled.ShoppingCart, contentDescription = "Корзина")
                    }
                }
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(products) { product ->
                ProductListItem(
                    product = product,
                    onItemClick = {
                        navController.navigate(Screen.ProductDetail.createRoute(product.id))
                    }
                )
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun ProductListItem(
    product: Product,
    onItemClick: (Product) -> Unit,
    modifier: Modifier = Modifier,
    priceFormatter: PriceFormatter = DefaultPriceFormatter()
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick(product) }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(product.name, style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.weight(1f))

        if (product.hasDiscount()) {
            PriceView(
                price = product.calcPrice(withDiscount = false),
                formatter = priceFormatter,
                color = MaterialTheme.colorScheme.error,
                textDecoration = TextDecoration.LineThrough
            )

            Spacer(modifier = Modifier.width(12.dp))
        }

        PriceView(
            price = product.calcPrice(),
            formatter = priceFormatter
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CatalogScreenPreview() {
    FirstTheme {
        val navController = NavController(LocalContext.current)
        CatalogScreen(navController = navController, products = sampleProducts)
    }
}