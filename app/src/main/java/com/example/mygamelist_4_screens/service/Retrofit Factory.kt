package com.example.mygamelist_4_screens.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private val URL = "https://localhost:44335/"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getGameService(): GameService {
        return retrofitFactory.create(GameService::class.java)
    }
}