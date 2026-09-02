package com.example.gamecore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.gamecore.ui.theme.GameCoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameCoreTheme {
                Game()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameCorePreview() {
    GameCoreTheme {
        KartScreen()
    }
}
