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
import com.example.zumer.kamino.adapters.ResidentsListAdapter
import com.example.zumer.kamino.model.Resident
import com.example.zumer.kamino.view_model.KaminoDataViewModel
import kotlinx.android.synthetic.main.fragment_residents_list.*

class ResidentsListFragment : Fragment(){

    private lateinit var kaminoDataViewModel: KaminoDataViewModel
    private var residents: ArrayList<Resident>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_residents_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.residents_list_title)
        kaminoDataViewModel = ViewModelProviders.of(this).get(KaminoDataViewModel::class.java)

        val arrayResidentsHttp = this.arguments?.getStringArrayList("ResidentsHttp")
        kaminoDataViewModel.loadResidentData(arrayResidentsHttp)
        kaminoDataViewModel.getResidentData().observe(this, Observer { residents ->
            this.residents = residents
            val adapter = ResidentsListAdapter(residents) { resident -> onResidentSelected(resident, view)}
            rv_list_residents_fragment.adapter = adapter
        })
    }

    private fun onResidentSelected(resident: Resident, view: View){
        val bundle = Bundle()
        bundle.putSerializable("ResidentDetail", resident)
        Navigation.findNavController(view).navigate(R.id.action_residents_list_to_resident_details, bundle)
    }
}