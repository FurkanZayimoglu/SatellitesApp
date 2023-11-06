package com.example.satellitesapp.ui.satellitedetail

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.satellitesapp.R
import com.example.satellitesapp.data.model.PositionData
import com.example.satellitesapp.data.model.SatelliteDetailData
import com.example.satellitesapp.databinding.FragmentSatelliteDetailBinding
import com.example.satellitesapp.utils.Resource
import com.example.satellitesapp.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SatelliteDetailFragment : Fragment(R.layout.fragment_satellite_detail) {

    private val args: SatelliteDetailFragmentArgs by navArgs()
    private val binding by viewBinding(FragmentSatelliteDetailBinding::bind)
    private val satellitesDetailViewModel: SatelliteDetailViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailObserve()
    }

    private fun detailObserve() {
        satellitesDetailViewModel.getSatelliteDetailAndPosition(args.satellite.id)
        satellitesDetailViewModel.satelliteDetailAndPosition.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.linearLayoutData.visibility = View.VISIBLE
                    setDetailView(it.data)
                }

                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    AlertDialog.Builder(requireContext())
                        .setTitle("Warning")
                        .setMessage("Not found values.")
                        .setPositiveButton("Close") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create().show()
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setDetailView(detail: Pair<SatelliteDetailData, List<PositionData?>>) {
        with(binding) {
            tvSatelliteName.text = args.satellite.name
            tvSatelliteDate.text = detail.first.firstFlight
            tvHeightMass.text = "${detail.first.height}/${detail.first.mass}"
            tvCost.text = detail.first.costPerLaunch.toString()
            CoroutineScope(Dispatchers.Main).launch {
                while (true) {
                    for (index in detail.second) {
                        tvLastPosition.text = "${index?.posX}/${index?.posY}"
                        delay(3000)
                    }
                }
            }
        }
    }
}