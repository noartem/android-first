package ru.noartem.first

import android.icu.util.ULocale
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import ru.noartem.first.ui.theme.FirstTheme


@Composable
fun PriceView(
    price: Double,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    textDecoration: TextDecoration = TextDecoration.None,
    formatter: PriceFormatter = DefaultPriceFormatter()
) {
    Text(
        text = formatter.format(price),
        modifier = modifier,
        color = color,
        textDecoration = textDecoration,
    )
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
