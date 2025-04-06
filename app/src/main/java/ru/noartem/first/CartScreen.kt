package ru.noartem.first

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.noartem.first.ui.theme.FirstTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    navController: NavController,
    cart: Cart,
    priceFormatter: PriceFormatter = DefaultPriceFormatter(),
    removeFromCart: (Product) -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Корзина") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            if (cart.products.isEmpty()) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Корзина пустая")
                }
            } else {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(cart.products) { product ->
                        CartItem(
                            product = product,
                            priceFormatter = priceFormatter,
                            remove = {
                                removeFromCart(product)
                            },
                        )
                        HorizontalDivider()
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                OrderSummaryRow(
                    "Всего:",
                    priceFormatter.format(cart.calcTotalPrice()),
                    isTotal = true
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { navController.navigate(Screen.OrderConfirmation.route) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Перейти к оформлению")
                }
            }
        }
    }
}

@Composable
fun CartItem(
    product: Product,
    priceFormatter: PriceFormatter,
    modifier: Modifier = Modifier,
    remove: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(product.name, modifier = Modifier.weight(1f))

        PriceView(
            price = product.price * (1 - product.discountPercent / 100.0),
            formatter = priceFormatter,
            modifier = Modifier.padding(start = 8.dp)
        )

        IconButton(onClick = { remove() }) {
            Icon(Icons.Filled.Delete, contentDescription = "Удалить")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartScreenPreview() {
    FirstTheme {
        val navController = NavController(LocalContext.current)
        val cart = Cart(
            mutableListOf(
                sampleProducts[0],
                sampleProducts[1].copy(discountPercent = 0)
            )
        )
        CartScreen(navController = navController, cart = cart)
    }
}

@Preview(showBackground = true)
@Composable
fun CartScreenEmptyPreview() {
    FirstTheme {
        val navController = NavController(LocalContext.current)
        val cart = Cart()
        CartScreen(navController = navController, cart = cart)
    }
}