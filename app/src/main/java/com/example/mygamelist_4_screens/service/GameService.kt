package com.example.mygamelist_4_screens.service

import com.example.mygamelist_4_screens.model.Game
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GameService {
    @GET("api/appdetails")
    fun fetchGameDetails(@Query("appids") appId: Int): Call<Map<String, Game>>
}