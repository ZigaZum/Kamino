package com.example.zumer.kamino.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.zumer.kamino.R
import com.example.zumer.kamino.model.Resident
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_resident_details.*
import java.lang.Exception

class ResidentDetailsFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_resident_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val resident = this.arguments?.getSerializable("ResidentDetail") as Resident
        fillResidentData(resident)
    }

    private fun fillResidentData(resident: Resident){
        with(resident){
            (activity as AppCompatActivity).supportActionBar?.title = name
            val heightTitle = "${getString(R.string.height)} $height cm"
            tv_height_details_fragment.text = heightTitle
            val massTitle = "${getString(R.string.mass)} $mass KG"
            tv_mass_details_fragment.text = massTitle
            val hairTitle = "${getString(R.string.hair_color)} $hair_color"
            tv_hair_details_fragment.text = hairTitle
            val skinTitle = "${getString(R.string.skin_color)} $skin_color"
            tv_skin_details_fragment.text = skinTitle
            val eyeTitle = "${getString(R.string.eye_color)} $eye_color"
            tv_eye_details_fragment.text = eyeTitle
            val birthTitle = "${getString(R.string.birth_year)} $birth_year"
            tv_birth_details_fragment.text = birthTitle
            val genderTitle = "${getString(R.string.gender)} $gender"
            tv_gender_details_fragment.text = genderTitle
            val homeTitle = "${getString(R.string.home_world)} Kamino"
            tv_home_details_fragment.text = homeTitle

            val picasso = Picasso.Builder(context!!).listener{_, _, e -> e.printStackTrace()}.build()
            picasso.load(image_url).resize(72, 72).centerCrop().into(iv_resident_pic_details_fragment, object : Callback{
                override fun onSuccess() {
                    iv_resident_pic_details_fragment.visibility = View.VISIBLE
                    tv_empty_picture.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    iv_resident_pic_details_fragment?.visibility = View.GONE
                    tv_empty_picture?.visibility = View.VISIBLE
                }
            })
        }
    }
}