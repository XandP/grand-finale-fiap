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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide
import com.example.mygamelist_4_screens.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameReview(
    navController: NavController,
    gameName: String,
    gameDesc: String,
    gameImageUrl: String
) {
    var review by remember { mutableStateOf("") }
    var reviewError by remember { mutableStateOf(false) }

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

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = review,
                onValueChange = {
                    review = it
                    if (review.isNotEmpty()) reviewError = false
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Sua review: $gameName", fontSize = 20.sp)
                },
                isError = reviewError,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
            )
            if (reviewError) {
                Text(
                    text = "Review está vazia!",
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Red,
                    textAlign = TextAlign.End
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if (review.isEmpty()) {
                    reviewError = true
                }

                if (!reviewError) {
                    navController.navigate("Home")
                }
            }) {
                Text(
                    text = stringResource(id = R.string.enter),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

            Button(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .padding(16.dp)
                    .size(width = 200.dp, height = 48.dp)
            ) {
                Text(text = "Voltar")
            }
        }
    }
}
