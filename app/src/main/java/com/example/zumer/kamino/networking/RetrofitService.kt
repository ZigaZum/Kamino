package com.example.zumer.kamino.networking

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.zumer.kamino.model.LikeModel
import com.example.zumer.kamino.model.Planet
import com.example.zumer.kamino.model.Resident
import com.google.gson.GsonBuilder
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService{
    val livePlanetResponse: MutableLiveData<Planet> = MutableLiveData()

    companion object Facotry {
        private const val BASE_URL = "http://private-84e428-starwars2.apiary-mock.com/"
        private val gson = GsonBuilder().setLenient().create()
        fun create(): KaminoApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(KaminoApi::class.java)
        }
    }

    fun loadPlanetData(): MutableLiveData<Planet>{
        val retrofitCall = create().getKaminoData()
        retrofitCall.enqueue(object : Callback<Planet>{
            override fun onFailure(call: Call<Planet>, t: Throwable) {
                Log.e("onFailure", "retrofit error")
            }

            override fun onResponse(call: Call<Planet>, response: Response<Planet>) {
                if (response.isSuccessful){
                    livePlanetResponse.value = response.body()
                }
            }
        })
        return livePlanetResponse
    }

    fun loadResidentData(urls: ArrayList<String>?): ArrayList<Resident?>{
        val arrayList: ArrayList<Resident?> = ArrayList()
        urls?.forEach {
            val retrofitCall = create().getResident(it)
            arrayList.add(retrofitCall.execute().body())
        }
        return arrayList
    }

    fun postLike(likeModel: LikeModel){
        val retrofitCall = create().likeKamino(likeModel)
        retrofitCall.enqueue(object  : Callback<JSONObject>{
            override fun onFailure(call: Call<JSONObject>, t: Throwable) {
                Log.e("onFailure", "retrofit error")
            }

            override fun onResponse(call: Call<JSONObject>, response: Response<JSONObject>) {
                //Should call for refreshing data on planet likes in fragment
                //loadPlanetData()
            }

        })
    }
}