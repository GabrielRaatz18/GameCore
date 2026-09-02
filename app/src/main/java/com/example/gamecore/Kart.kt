package com.example.gamecore

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun KartScreen(
    cartCount: Int = 3
) {
    val context = LocalContext.current
    var couponCode by remember { mutableStateOf("") }
    GameCorePage(
        selectedDestination = GameCoreDestination.CART,
        cartCount = cartCount,
        onDestinationClick = {},
        topBar = {
            GameCoreTopBar(
                title = "Carrinho",
                subtitle = "$cartCount itens",
                showBack = true,
                showCartAction = false,
                onBackClick = {}
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = GameCoreDimens.ScreenPadding)
        ) {
            Spacer(Modifier.height(18.dp))

            Text(
                text = "Faça o pagamento",
                color = GameCoreColors.TextPrimary,
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Revise seus jogos antes de finalizar a compra.",
                color = GameCoreColors.TextSecondary,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.height(18.dp))

            KartItem(
                title = "Grand Theft Auto VI",
                edition = "Edição Standard",
                category = "Ação • Aventura",
                price = "R$ 550,90",
                imageRes = R.drawable.gta6_banner
            )
            Spacer(Modifier.height(10.dp))
            KartItem(
                title = "The Last of Us",
                edition = "Edição Standard",
                category = "Terro • Aventura",
                price = "R$ 138,90",
                imageRes = R.drawable.last
            )
            Spacer(Modifier.height(10.dp))
            KartItem(
                title = "EA SPORTS FC™ 27",
                edition = "Edição Standard",
                category = "Esporte • Simulação",
                price = "R$ 299,00",
                imageRes = R.drawable.fc27
            )

            Spacer(Modifier.height(14.dp))
            Text(
                text = "←  Continuar comprando",
                color = GameCoreColors.Orange,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .align(Alignment.Start)
                    .clickable(onClick = {})
                    .padding(vertical = 6.dp)
            )

            Spacer(Modifier.height(GameCoreDimens.SectionSpacing))

            GameCoreSectionHeader(title = "Resumo")
            Spacer(Modifier.height(10.dp))
            KartSummaryCard()

            Spacer(Modifier.height(GameCoreDimens.SectionSpacing))

            GameCoreSectionHeader(title = "Método de pagamento")
            Spacer(Modifier.height(10.dp))
            KartPaymentMethod()

            Spacer(Modifier.height(GameCoreDimens.SectionSpacing))

            OutlinedTextField(
                value = couponCode,
                onValueChange = { couponCode = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Cupom de desconto") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = GameCoreColors.Orange,
                    unfocusedBorderColor = GameCoreColors.Border,
                    focusedLabelColor = GameCoreColors.Orange,
                    unfocusedLabelColor = GameCoreColors.TextSecondary,
                    focusedTextColor = GameCoreColors.TextPrimary,
                    unfocusedTextColor = GameCoreColors.TextPrimary
                ),
                singleLine = true
            )

            Spacer(Modifier.height(16.dp))
            GameCorePrimaryButton(
                text = "Finalizar pagamento",
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = R.drawable.ic_lock,
                onClick = {
                    Toast.makeText(context, "Processando pagamento...", Toast.LENGTH_LONG).show()
                }
            )
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_lock),
                    contentDescription = null,
                    tint = GameCoreColors.TextDisabled,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(Modifier.width(5.dp))
                Text(
                    text = "Pagamento seguro",
                    color = GameCoreColors.TextDisabled,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
fun Kart() = KartScreen()

@Composable
private fun KartItem(
    title: String,
    edition: String,
    category: String,
    price: String,
    @DrawableRes imageRes: Int?
) {
    GameCoreCard {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GameCoreImage(
                imageRes = imageRes,
                modifier = Modifier.size(width = 82.dp, height = 104.dp),
                label = "CAPA",
                cornerRadius = 10
            )

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    color = GameCoreColors.TextPrimary,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(3.dp))
                Text(
                    text = edition,
                    color = GameCoreColors.TextSecondary,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = category,
                    color = GameCoreColors.TextDisabled,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(Modifier.height(10.dp))
                GameCorePrice(price = price, fontSize = 16)
            }

            Surface(
                modifier = Modifier.size(36.dp),
                color = Color(0xFF211719),
                shape = RoundedCornerShape(10.dp),
                onClick = { }
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        painter = painterResource(R.drawable.ic_delete),
                        contentDescription = "Remover",
                        tint = GameCoreColors.Error,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun KartSummaryCard() {
    GameCoreCard {
        Column(modifier = Modifier.padding(16.dp)) {
            KartPriceRow("Subtotal", "R$ 1099,80")
            Spacer(Modifier.height(10.dp))
            KartPriceRow("Desconto", "-R$ 111,00", GameCoreColors.Success)
            Spacer(Modifier.height(12.dp))
            HorizontalDivider(color = GameCoreColors.Border)
            Spacer(Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total",
                    color = GameCoreColors.TextPrimary,
                    style = MaterialTheme.typography.titleMedium
                )
                GameCorePrice(
                    price = "R$ 988,80",
                    color = GameCoreColors.Orange,
                    fontSize = 21
                )
            }
        }
    }
}

@Composable
private fun KartPriceRow(
    label: String,
    value: String,
    valueColor: Color = GameCoreColors.TextPrimary
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = GameCoreColors.TextSecondary,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = value,
            color = valueColor,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun KartPaymentMethod() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        color = GameCoreColors.CardElevated,
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, GameCoreColors.Border),
        onClick = { }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Selecione uma forma de pagamento",
                color = GameCoreColors.TextSecondary,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )
            Icon(
                painter = painterResource(R.drawable.ic_chevron_down),
                contentDescription = null,
                tint = GameCoreColors.TextSecondary,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
private fun KartPreview() {
    GameCoreTheme { KartScreen() }
}
