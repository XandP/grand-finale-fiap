package com.example.mygamelist_4_screens.repository

import android.widget.ImageView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Button
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide

@Composable
fun GameInfo(
    navController: NavController,
    gameName: String,
    gameDesc: String,
    gameImageUrl: String
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AndroidView(
                factory = { context ->
                    val imageView = ImageView(context)
                    imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                    imageView
                },
                update = { imageView ->
                    Glide.with(imageView)
                        .load(gameImageUrl)
                        .into(imageView)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Text("Nome do Jogo: $gameName", fontSize = 20.sp)
            Text("Descricao: $gameDesc", fontSize = 20.sp)

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.navigate("GameReview?gameName=$gameName&gameDesc=$gameDesc&gameImage=$gameImageUrl")
                },
                modifier = Modifier
                    .padding(16.dp)
                    .size(width = 200.dp, height = 48.dp)
            ) {
                Text(text = "Avaliar")
            }

            Button(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .padding(16.dp)
                    .size(width = 200.dp, height = 48.dp)
            ) {
                Text(text = "Retornar")
            }
        }
    }
}
