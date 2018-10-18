package com.example.zumer.kamino.networking

import com.example.zumer.kamino.model.LikeModel
import com.example.zumer.kamino.model.Planet
import com.example.zumer.kamino.model.Resident
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface KaminoApi {
    @GET("planets/10")
    fun getKaminoData(): Call<Planet>

    @GET
    fun getResident(@Url url: String): Call<Resident>

    @POST("planets/10/like")
    fun likeKamino(@Body likeModel: LikeModel): Call<JSONObject>
}