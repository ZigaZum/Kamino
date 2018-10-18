package com.example.zumer.kamino.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.zumer.kamino.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_kamino_big_pic.*

class KaminoBigPicFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kamino_big_pic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val pictureHtto = this.arguments?.getString("PictureHTTP")
        Picasso.get().load(pictureHtto).into(iv_big_picture_fragment)
    }
}