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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StoreScreen(
    cartCount: Int = 3
) {
    val categories = listOf("Destaques", "Ação", "RPG", "Corrida", "Indie", "Multiplayer", "Terror")

    GameCorePage(
        selectedDestination = GameCoreDestination.STORE,
        cartCount = cartCount,
        onDestinationClick = {},
        topBar = {
            GameCoreTopBar(
                title = "GameCore",
                subtitle = "Encontre seu próximo jogo",
                cartCount = cartCount,
                onCartClick = {}
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = GameCoreDimens.ScreenPadding)
        ) {
            Spacer(Modifier.height(16.dp))

            GameCoreSearchBar()

            Spacer(Modifier.height(14.dp))

            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                categories.forEach { category ->
                    GameCoreCategoryChip(
                        text = category,
                        selected = category == "Destaques",
                        onClick = {}
                    )
                }
            }

            Spacer(Modifier.height(GameCoreDimens.SectionSpacing))

            StoreHeroCard(
                title = "Elden Ring",
                category = "Ação • RPG",
                price = "R$ 249,90",
                imageRes = R.drawable.elden
            )

            Spacer(Modifier.height(GameCoreDimens.SectionSpacing))

            GameCoreSectionHeader(
                title = "Ofertas para você",
                action = "Ver todas"
            )
            Spacer(Modifier.height(10.dp))

            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StoreOfferCard(
                    title = "Red Dead Redemption 2",
                    oldPrice = "R$ 299,90",
                    price = "R$ 74,90",
                    discount = "-75%",
                    imageRes = R.drawable.read
                )
                StoreOfferCard(
                    title = "Call of Duty: Black Ops 6",
                    oldPrice = "R$ 339,90",
                    price = "R$ 306,00",
                    discount = "-10%",
                    imageRes = R.drawable.call
                )
                StoreOfferCard(
                    title = "The Last of Us",
                    oldPrice = "R$ 249,90",
                    price = "R$ 138,90",
                    discount = "-53%",
                    imageRes = R.drawable.last
                )
            }

            Spacer(Modifier.height(GameCoreDimens.SectionSpacing))

            GameCoreSectionHeader(title = "Mais jogados")
            Spacer(Modifier.height(10.dp))

            StorePopularGameCard(
                title = "Grand Theft Auto VI",
                category = "Ação • Crime • Mundo Aberto",
                price = "R$ 550,90",
                imageRes = R.drawable.gta6_banner
            )
            Spacer(Modifier.height(10.dp))
            StorePopularGameCard(
                title = "Counter-Strike 2",
                category = "FPS • Ação",
                price = "R$ 74,90",
                imageRes = R.drawable.cs2
            )
            Spacer(Modifier.height(10.dp))
            StorePopularGameCard(
                title = "EA SPORTS FC™ 27",
                category = "Esporte • Simulação",
                price = "R$ 299,00",
                imageRes = R.drawable.fc27
            )

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
fun Store() = StoreScreen()

@Composable
private fun StoreHeroCard(
    title: String,
    category: String,
    price: String,
    @DrawableRes imageRes: Int?
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(238.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = GameCoreColors.Card),
        border = androidx.compose.foundation.BorderStroke(1.dp, GameCoreColors.Border)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            GameCoreImage(
                imageRes = imageRes,
                modifier = Modifier.fillMaxSize(),
                label = "BANNER DO JOGO",
                cornerRadius = 16
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(heroOverlayBrush())
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = title,
                    color = GameCoreColors.TextPrimary,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = category,
                    color = GameCoreColors.TextSecondary,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    GameCorePrice(price = price, fontSize = 19)
                    Spacer(Modifier.width(12.dp))
                    GameCorePrimaryButton(
                        text = "Ver jogo",
                        modifier = Modifier.width(116.dp),
                        onClick = {}
                    )
                }
            }

            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                repeat(3) { index ->
                    Box(
                        modifier = Modifier
                            .width(if (index == 0) 18.dp else 7.dp)
                            .height(7.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                if (index == 0) GameCoreColors.Orange
                                else GameCoreColors.NavInactive
                            )
                    )
                }
            }
        }
    }
}

@Composable
private fun StoreOfferCard(
    title: String,
    oldPrice: String,
    price: String,
    discount: String,
    @DrawableRes imageRes: Int?
) {
    Card(
        modifier = Modifier.width(184.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = GameCoreColors.Card),
        border = androidx.compose.foundation.BorderStroke(1.dp, GameCoreColors.Border)
    ) {
        Column {
            Box {
                GameCoreImage(
                    imageRes = imageRes,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(112.dp),
                    label = "CAPA",
                    cornerRadius = 14
                )
                Surface(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp),
                    color = GameCoreColors.Orange,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = discount,
                        color = Color.Black,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
                    )
                }
            }

            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = title,
                    color = GameCoreColors.TextPrimary,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(7.dp))
                Text(
                    text = oldPrice,
                    color = GameCoreColors.TextDisabled,
                    style = MaterialTheme.typography.bodySmall,
                    textDecoration = TextDecoration.LineThrough
                )
                GameCorePrice(
                    price = price,
                    color = GameCoreColors.Orange,
                    fontSize = 17
                )
            }
        }
    }
}

@Composable
private fun StorePopularGameCard(
    title: String,
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
                modifier = Modifier.size(width = 100.dp, height = 72.dp),
                label = "CAPA",
                cornerRadius = 10
            )
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    color = GameCoreColors.TextPrimary,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.height(3.dp))
                Text(
                    text = category,
                    color = GameCoreColors.TextSecondary,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(Modifier.height(7.dp))
                GameCorePrice(price = price, color = GameCoreColors.Orange, fontSize = 16)
            }
            Surface(
                modifier = Modifier.size(36.dp),
                color = GameCoreColors.CardElevated,
                shape = RoundedCornerShape(10.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    androidx.compose.material3.Icon(
                        painter = androidx.compose.ui.res.painterResource(R.drawable.ic_favorite),
                        contentDescription = "Favoritar",
                        tint = GameCoreColors.TextSecondary,
                        modifier = Modifier.size(19.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
private fun StorePreview() {
    GameCoreTheme { StoreScreen() }
}
