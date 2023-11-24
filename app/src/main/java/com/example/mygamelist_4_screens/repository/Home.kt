package com.example.mygamelist_4_screens.repository


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mygamelist_4_screens.model.Game
import com.example.mygamelist_4_screens.service.GameService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.widget.ImageView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide
import kotlin.collections.ArrayList
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController) {

    val gameMessages = remember { mutableStateListOf<String>() }
    val gameMessagesTitle = remember { mutableStateListOf<String>() }
    val gameMessagesDesc = remember { mutableStateListOf<String>() }
    val gameMessagesImg = remember { mutableStateListOf<String>() }

    val retrofit = Retrofit.Builder()
        .baseUrl("https://store.steampowered.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(GameService::class.java)

    val gameIds = listOf(
        730, 1086940, 374320, 271590, 1716740, 105600, 1091500, 289070, 1971870
    )


    gameIds.forEach { appId ->
        val call = apiService.fetchGameDetails(appId)


        call.enqueue(object : Callback<Map<String, Game>> {
            override fun onResponse(call: Call<Map<String, Game>>, response: Response<Map<String, Game>>) {
                if (response.isSuccessful) {
                    val gameResponseMap = response.body()
                    if (gameResponseMap != null && gameResponseMap.containsKey(appId.toString())) {
                        val gameResponse = gameResponseMap[appId.toString()]
                        if (gameResponse != null) {
                            val gameData = gameResponse.data
                            val gameName = gameData.name
                            val gameDescription = gameData.short_description
                            val header_image = gameData.header_image

                            gameMessagesTitle.add(gameName)
                            gameMessagesDesc.add(gameDescription)
                            gameMessagesImg.add(header_image)
                        }
                    }
                }

                else {
                    val errorMessage = "API call failed with code ${response.code()}"
                    gameMessages.add(errorMessage)
                }
            }

            override fun onFailure(call: Call<Map<String, Game>>, t: Throwable) {
                val errorMessage = "API call failed with exception: ${t.message}"
                gameMessages.add(errorMessage)
            }
        })

        GameScreen2(navController, gameMessagesTitle, gameMessagesDesc, gameMessagesImg)
    }
}






@Composable
fun GameScreen2(
    navController: NavController,
    gameNames: List<String>,
    gameDescriptions: List<String>,
    gameImageUrls: List<String>
) {
    LazyColumn (
        modifier = Modifier.background(Color.hsv(0.6f,0.3f,0.1f))
    ) {
        items(gameNames.size) { index ->
            val gameName = gameNames.getOrNull(index) ?: ""
            val gameDescription = gameDescriptions.getOrNull(index) ?: ""
            val gameImageUrl = gameImageUrls.getOrNull(index) ?: ""

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.hsv(0.6f,0.3f,0.2f),
                shape = RoundedCornerShape(8.dp))
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                    .clickable(onClick = {
                        navController.navigate("GameInfo?gameName=$gameName&gameDesc=$gameDescription&gameImage=$gameImageUrl")
                    })
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
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

                    Text(
                        text = gameName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(8.dp),
                        color = Color.Gray
                    )

                    Text(
                        text = gameDescription,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(8.dp),
                        color = Color.White
                    )
                }
            }
        }
    }
}