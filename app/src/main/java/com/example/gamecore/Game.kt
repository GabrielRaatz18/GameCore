package com.example.gamecore

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun GameScreen(
    cartCount: Int = 3
) {
    val context = LocalContext.current
    var isFavorite by remember { mutableStateOf(false) }
    GameCorePage(
        selectedDestination = null,
        cartCount = cartCount,
        onDestinationClick = {},
        topBar = {
            GameCoreTopBar(
                title = "GameCore",
                showBack = true,
                showSearchAction = true,
                showCartAction = true,
                cartCount = cartCount,
                onBackClick = {},
                onCartClick = {}
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            GameHeroBanner(imageRes = R.drawable.gta6_banner)

            Column(modifier = Modifier.padding(GameCoreDimens.ScreenPadding)) {
                Text(
                    text = "GRAND THEFT AUTO VI",
                    color = GameCoreColors.TextPrimary,
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Ação • Crime • Mundo Aberto",
                    color = GameCoreColors.TextSecondary,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(Modifier.height(10.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    repeat(5) {
                        Icon(
                            painter = painterResource(R.drawable.ic_star),
                            contentDescription = null,
                            tint = GameCoreColors.Orange,
                            modifier = Modifier.size(17.dp)
                        )
                        Spacer(Modifier.width(2.dp))
                    }
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "5,0",
                        color = GameCoreColors.TextPrimary,
                        style = MaterialTheme.typography.labelLarge
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "10M+ avaliações",
                        color = GameCoreColors.TextDisabled,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(Modifier.height(20.dp))

                GameCorePrice(price = "R$ 550,90", fontSize = 22)
                Spacer(Modifier.height(10.dp))
                GameCorePrimaryButton(
                    text = "Adicionar ao carrinho",
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = R.drawable.ic_cart,
                    onClick = {
                        Toast.makeText(context, "Jogo adicionado ao carrinho!", Toast.LENGTH_SHORT).show()
                    }
                )
                Spacer(Modifier.height(8.dp))
                GameCoreSecondaryButton(
                    text = if (isFavorite) "Favorito" else "Lista de desejos",
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = if (isFavorite) R.drawable.ic_star else R.drawable.ic_favorite,
                    onClick = {
                        isFavorite = !isFavorite
                        val msg = if (isFavorite) "Adicionado aos favoritos" else "Removido dos favoritos"
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    }
                )

                Spacer(Modifier.height(GameCoreDimens.SectionSpacing))

                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    GameCoreInfoChip("Single Player")
                    GameCoreInfoChip("Multiplayer")
                    GameCoreInfoChip("4K Ultra HD")
                    GameCoreInfoChip("Ray Tracing")
                    GameCoreInfoChip("Português")
                }

                Spacer(Modifier.height(GameCoreDimens.SectionSpacing))

                GameCoreSectionHeader(title = "Sobre este jogo")
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Grand Theft Auto VI viaja para o estado de Leonida, lar das ruas ensolaradas de Vice City e além, na maior e mais envolvente evolução da série Grand Theft Auto até agora.",
                    color = GameCoreColors.TextSecondary,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = "Ver mais",
                    color = GameCoreColors.Orange,
                    style = MaterialTheme.typography.labelLarge
                )

                Spacer(Modifier.height(GameCoreDimens.SectionSpacing))

                GameCoreSectionHeader(title = "Capturas de tela")
                Spacer(Modifier.height(10.dp))
                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    val screenshots = listOf(
                        R.drawable.gta6_cap1,
                        R.drawable.gta6_cap2,
                        R.drawable.gta6_cap3
                    )
                    screenshots.forEachIndexed { index, screenshot ->
                        GameCoreImage(
                            imageRes = screenshot,
                            modifier = Modifier
                                .width(220.dp)
                                .height(124.dp),
                            label = "CAPTURA ${index + 1}",
                            cornerRadius = 12
                        )
                    }
                }

                Spacer(Modifier.height(GameCoreDimens.SectionSpacing))

                GameCoreSectionHeader(title = "Informações")
                Spacer(Modifier.height(10.dp))
                GameInfoCard()

                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun Game() = GameScreen()

@Composable
private fun GameHeroBanner(@DrawableRes imageRes: Int?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        GameCoreImage(
            imageRes = imageRes,
            modifier = Modifier.fillMaxSize(),
            label = "BANNER DO JOGO",
            cornerRadius = 0
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(heroOverlayBrush())
        )
        Surface(
            modifier = Modifier
                .align(Alignment.Center)
                .size(58.dp),
            shape = CircleShape,
            color = Color(0xCC151A20),
            border = androidx.compose.foundation.BorderStroke(1.dp, GameCoreColors.Border)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    painter = painterResource(R.drawable.ic_play),
                    contentDescription = "Assistir trailer",
                    tint = GameCoreColors.Orange,
                    modifier = Modifier.size(25.dp)
                )
            }
        }
    }
}

@Composable
private fun GameInfoCard() {
    GameCoreCard {
        Column(modifier = Modifier.padding(14.dp)) {
            GameInfoRow("Desenvolvedora", "Rockstar Games")
            Spacer(Modifier.height(10.dp))
            GameInfoRow("Lançamento", "2025")
            Spacer(Modifier.height(10.dp))
            GameInfoRow("Classificação", "18 anos")
            Spacer(Modifier.height(10.dp))
            GameInfoRow("Plataforma", "PS5, Xbox Series X/S")
        }
    }
}

@Composable
private fun GameInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = GameCoreColors.TextSecondary,
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = value,
            color = GameCoreColors.TextPrimary,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
private fun GamePreview() {
    GameCoreTheme { GameScreen() }
}
