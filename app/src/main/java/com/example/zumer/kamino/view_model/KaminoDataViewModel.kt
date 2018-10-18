package com.example.zumer.kamino.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zumer.kamino.model.LikeModel
import com.example.zumer.kamino.model.Planet
import com.example.zumer.kamino.model.Resident
import com.example.zumer.kamino.networking.RetrofitService
import kotlinx.coroutines.experimental.launch

class KaminoDataViewModel : ViewModel(){

    private var planet: MutableLiveData<Planet> = MutableLiveData()
    private var residents: MutableLiveData<ArrayList<Resident?>> = MutableLiveData()
    private var array: ArrayList<String> = ArrayList()
    private val mService = RetrofitService()

    fun loadPlanetData(){
        planet = mService.loadPlanetData()
    }

    fun loadResidentData(urls: ArrayList<String>?){
        launch {
            residents.postValue(mService.loadResidentData(urls))
        }
    }

    fun getPlanet(): LiveData<Planet>{
        return planet
    }

    fun getResidentData(): LiveData<ArrayList<Resident>>{
        return residents as LiveData<ArrayList<Resident>>
    }

    fun postLike(likeModel: LikeModel){
        mService.postLike(likeModel)
    }
}