package ru.noartem.first

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.noartem.first.ui.theme.FirstTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@Composable
fun OrderConfirmationScreen(
    cart: Cart,
    modifier: Modifier = Modifier,
    priceFormatter: PriceFormatter = DefaultPriceFormatter(),
) {
    val ctx = LocalContext.current
    var nameState by remember { mutableStateOf("") }
    var nameFocused by remember { mutableStateOf(false) }
    var nameTouched by remember { mutableStateOf(false) }
    var phoneState by remember { mutableStateOf("") }
    var phoneFocused by remember { mutableStateOf(false) }
    var phoneTouched by remember { mutableStateOf(false) }

    val isNameValid = nameState.isNotBlank()
    val phoneDigits = phoneState.filter { it.isDigit() }
    val isPhoneValid = phoneState.length == 11 && phoneDigits.length == 11
    val isFormValid = isNameValid && isPhoneValid

    Box(modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 24.dp)
            ) {
                Text(
                    text = "Оформление заказа",
                    style = MaterialTheme.typography.headlineSmall,
                )
                Spacer(modifier = Modifier.width(12.dp))
                Image(
                    painter = painterResource(id = R.drawable.baseline_shopping_cart_24),
                    contentDescription = stringResource(id = R.string.shopping_cart)
                )
            }

            cart.calcTotalPrice()

            OrderSummaryRow(
                "Цена без скидки:",
                priceFormatter.format(cart.calcTotalPrice(discount = false))
            )
            OrderSummaryRow("Скидка:", priceFormatter.format(cart.calcTotalDiscount()))
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            OrderSummaryRow(
                "Итоговая цена:",
                priceFormatter.format(cart.calcTotalPrice()),
                isTotal = true
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = nameState,
                onValueChange = { nameState = it },
                label = { Text("ФИО") },
                placeholder = { Text("Например: Иванов Иван Иванович") },
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        if (focusState.isFocused) {
                            nameFocused = true
                        } else if (nameFocused) {
                            nameTouched = true
                        }
                    },
                singleLine = true,
                isError = nameTouched && !isNameValid,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                supportingText = {
                    if (nameTouched && !isNameValid) {
                        Text("ФИО не должно быть пустым", color = MaterialTheme.colorScheme.error)
                    }
                },
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = phoneState,
                onValueChange = { phoneState = it },
                label = { Text("Номер телефона") },
                placeholder = { Text("Например: 88005553535") },
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        if (focusState.isFocused) {
                            phoneFocused = true
                        } else if (nameFocused) {
                            phoneTouched = true
                        }
                    },
                singleLine = true,
                isError = phoneTouched && !isPhoneValid,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                supportingText = {
                    if (phoneTouched && !isPhoneValid) {
                        Text(
                            "Введите корректный номер телефона (11 цифр)",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    Toast.makeText(
                        ctx,
                        "Заказ для $nameState ($phoneState) на сумму ${priceFormatter.format(cart.calcTotalPrice())} успешно оформлен!",
                        Toast.LENGTH_LONG
                    ).show()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                enabled = isFormValid
            ) {
                Text("Подтвердить заказ")
            }
        }
    }
}

@Composable
fun OrderSummaryRow(label: String, value: String, isTotal: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OrderConfirmationScreenPreview() {
    FirstTheme {
        OrderConfirmationScreen(
            cart = Cart(
                listOf(
                    Product(name = "iPhone XR", price = 1299.99, discountPercent = 33)
                )
            ),
        )
    }
}