package com.example.satellitesapp.ui.satellite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.satellitesapp.data.model.SatelliteData
import com.example.satellitesapp.data.repository.SatelliteRepository
import com.example.satellitesapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatelliteViewModel @Inject constructor(
    private val repository: SatelliteRepository
) : ViewModel() {


    private val _satellites = MutableLiveData<Resource<List<SatelliteData>?>>()
    val satellites: LiveData<Resource<List<SatelliteData>?>> = _satellites

    init {
        getSatellites()
    }

    private fun getSatellites() = viewModelScope.launch {
        _satellites.value = Resource.Loading
        delay(1000)
        _satellites.value = repository.getSatellites()
    }


    fun searchSatellites(query: String) = viewModelScope.launch {
        if (query.length > 2) {
            _satellites.value = Resource.Loading
            delay(2000)
            _satellites.value = repository.searchSatellites(query)
        } else {
            _satellites.value = Resource.Loading
            _satellites.value = repository.getSatellites()
        }

    }
}