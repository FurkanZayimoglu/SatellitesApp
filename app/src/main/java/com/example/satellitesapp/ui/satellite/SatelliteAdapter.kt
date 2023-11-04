package com.example.satellitesapp.ui.satellite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.satellitesapp.data.model.SatelliteData
import com.example.satellitesapp.databinding.LayoutSatellitesItemBinding

class SatelliteAdapter : RecyclerView.Adapter<SatelliteAdapter.SatelliteViewHolder>() {

    private var satelliteList = ArrayList<SatelliteData>()
    var onClickListener: ((String, String) -> Unit)? = null

    fun setData(data: List<SatelliteData>) {
        satelliteList.clear()
        satelliteList.addAll(data)
        notifyItemRangeInserted(0, data.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SatelliteViewHolder =
        SatelliteViewHolder(
            LayoutSatellitesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )


    override fun onBindViewHolder(holder: SatelliteViewHolder, position: Int) {
        holder.bind(satelliteList[position])
    }

    override fun getItemCount() = satelliteList.size


    inner class SatelliteViewHolder(private val binding: LayoutSatellitesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(satelliteData: SatelliteData) {
            with(binding) {

            }
        }
    }
}


