package com.example.satellitesapp.ui.satellite

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.satellitesapp.R
import com.example.satellitesapp.databinding.FragmentSatelliteBinding
import com.example.satellitesapp.utils.Resource
import com.example.satellitesapp.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SatelliteFragment : Fragment(R.layout.fragment_satellite) {

    private val binding by viewBinding(FragmentSatelliteBinding::bind)
    private val satellitesViewModel: SatelliteViewModel by viewModels()
    private val satelliteAdapter by lazy { SatelliteAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        satellitesObserve()

    }

    private fun satellitesObserve(){
        satellitesViewModel.satellites.observe(viewLifecycleOwner){
             when(it){
                 is Resource.Success -> {
                     binding.progressBar.visibility = View.GONE
                     it.data?.let { satelliteData -> satelliteAdapter.setData(satelliteData) }
                     binding.rvSatellites.adapter = satelliteAdapter
                 }
                 is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    AlertDialog.Builder(requireContext())
                         .setTitle("Warning")
                         .setMessage(it.message)
                         .create().show()


                 }
                 Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                 else -> {


                 }
             }

    }


}





}