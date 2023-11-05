package com.example.satellitesapp.ui.satellite

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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
        searchSatellitesObserve()
        satelliteAdapter.onClickSatelliteItem = {
            findNavController().navigate(R.id.action_satelliteFragment_to_satelliteDetailFragment,
                Bundle().apply {
                    putParcelable("satellite", it)
                })
        }

    }

    private fun satellitesObserve(){
        satellitesViewModel.satellites.observe(viewLifecycleOwner){
             when(it){
                 is Resource.Success -> {
                     binding.progressBar.visibility = View.GONE
                     binding.cvSearchView.visibility = View.VISIBLE
                     binding.rvSatellites.visibility = View.VISIBLE
                     binding.rvSatellites.adapter = satelliteAdapter
                     it.data?.let { satelliteData -> satelliteAdapter.setData(satelliteData) }
                 }
                 is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                     AlertDialog.Builder(requireContext())
                         .setTitle("Warning")
                         .setMessage("Not found values.")
                         .setPositiveButton("Close"){ dialog, _ ->
                             dialog.dismiss()
                         }
                         .create().show()
                 }
                 is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                 else -> {
                 }
             }

    }
}

    private fun searchSatellitesObserve(){
         binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
             override fun onQueryTextSubmit(query: String?): Boolean {
                 return false
             }

             override fun onQueryTextChange(newText: String?): Boolean {
                 newText?.let {
                     satellitesViewModel.searchSatellites(it)
                 }
                 return false
             }
         })
    }




}