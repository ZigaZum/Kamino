package com.example.zumer.kamino.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zumer.kamino.R
import com.example.zumer.kamino.model.Resident
import kotlinx.android.synthetic.main.row_resident_item.view.*

class ResidentsListAdapter(private val residents: ArrayList<Resident>, val residentSelected: (Resident) -> Unit) : RecyclerView.Adapter<ResidentsListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_resident_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = residents.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onResidentBind(residents[position])
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun onResidentBind(resident: Resident){
            with(resident){
                itemView.tv_name_resident_row.text = name
                itemView.tv_name_resident_row.setOnClickListener { residentSelected(this) }
            }
        }
    }
}