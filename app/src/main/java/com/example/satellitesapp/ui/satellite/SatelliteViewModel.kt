package com.example.satellitesapp.ui.satellite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.satellitesapp.data.model.SatelliteData
import com.example.satellitesapp.data.repository.SatelliteRepository
import com.example.satellitesapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatelliteViewModel @Inject constructor(
    private val repository: SatelliteRepository
): ViewModel() {


    private val _satellites = MutableLiveData<Resource<List<SatelliteData>?>>(Resource.Loading)
    val satellites: LiveData<Resource<List<SatelliteData>?>> = _satellites

    init {
        getSatellites()
    }

    private fun getSatellites() = viewModelScope.launch {
        _satellites.value = Resource.Loading
        _satellites.value = repository.getSatellites()
    }


    private fun searchSatellites(){

    }
}