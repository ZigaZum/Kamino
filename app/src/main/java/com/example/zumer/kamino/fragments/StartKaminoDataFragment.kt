package com.example.zumer.kamino.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.zumer.kamino.R
import com.example.zumer.kamino.model.LikeModel
import com.example.zumer.kamino.model.Planet
import com.example.zumer.kamino.view_model.KaminoDataViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_start_kamino_data.*

class StartKaminoDataFragment : Fragment(), View.OnClickListener{

    private lateinit var kaminoDataViewModel: KaminoDataViewModel
    private var planet: Planet? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_start_kamino_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        kaminoDataViewModel = ViewModelProviders.of(this).get(KaminoDataViewModel::class.java)
        kaminoDataViewModel.loadPlanetData()
        kaminoDataViewModel.getPlanet().observe(this, Observer { result ->
            planet = result
            fillPlanetData(result)
        })
        iv_small_pic_start_fragment.setOnClickListener(this)
        iv_residentslist_start_fragment.setOnClickListener(this)
        tv_likes_start_fragment.setOnClickListener(this)
    }

    private fun fillPlanetData(planet: Planet){
        with(planet){
            (activity as AppCompatActivity).supportActionBar?.title = name
            val rotationTitle = "${getString(R.string.rotation_period)} $rotation_period"
            tv_rotation_period_start_fragment.text = rotationTitle
            val orbitalTitle = "${getString(R.string.orbital_period)} $orbital_period"
            tv_orbital_period_start_fragment.text = orbitalTitle
            val diameterTitle = "${getString(R.string.diameter)} $diameter"
            tv_diameter_start_fragment.text = diameterTitle
            val climateTitle = "${getString(R.string.climate)} $climate"
            tv_climate_start_fragment.text = climateTitle
            val gravityTitle = "${getString(R.string.gravity)} $gravity"
            tv_gravity_start_fragment.text = gravityTitle
            val terrainTitle = "${getString(R.string.terrain)} $terrain"
            tv_terrain_start_fragment.text = terrainTitle
            val surfaceWaterTitle = "${getString(R.string.surface_water)} $surface_water"
            tv_surface_water_start_fragment.text = surfaceWaterTitle
            val populationTitle = "${getString(R.string.population)} $population"
            tv_population_start_fragment.text = populationTitle
            val residentsTitle = "${getString(R.string.residents_number)} ${residents?.distinct()?.size}"
            tv_residents_start_fragment.text = residentsTitle
            val likesTitle = "${getString(R.string.likes)} $likes"
            tv_likes_start_fragment.text = likesTitle

            Picasso.get().load(image_url).resize(100, 100).centerCrop().into(iv_small_pic_start_fragment)
        }

    }
    override fun onClick(view: View?) {
        when(view?.id){
            iv_small_pic_start_fragment.id -> {
                val bundle = Bundle()
                bundle.putString("PictureHTTP", planet?.image_url)
                Navigation.findNavController(view).navigate(R.id.action_start_kamino_data_to_kamino_big_pic, bundle)
            }
            iv_residentslist_start_fragment.id -> {
                val bundle = Bundle()
                bundle.putStringArrayList("ResidentsHttp", planet?.residents?.distinct() as ArrayList<String>?)
                Navigation.findNavController(view).navigate(R.id.action_start_kamino_data_to_residents_list, bundle)
            }
            tv_likes_start_fragment.id -> {
                val prefs = activity?.getSharedPreferences("", 0)
                if (!prefs?.getBoolean("Kamino liked", false)!!){
                    val likeModel = LikeModel(planet?.likes!! + 1)
                    kaminoDataViewModel.postLike(likeModel)
                    val likesTitle = "${getString(R.string.likes)} ${planet?.likes!! + 1}"
                    tv_likes_start_fragment.text = likesTitle
                    prefs.edit().putBoolean("Kamino liked", true).apply()
                }else{
                    Snackbar.make(tv_likes_start_fragment, "${getString(R.string.already_voted)} ${planet?.name}", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }
}