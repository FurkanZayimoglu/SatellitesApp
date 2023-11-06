package com.example.satellitesapp.ui.satellitedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.satellitesapp.data.model.PositionData
import com.example.satellitesapp.data.model.SatelliteDetailData
import com.example.satellitesapp.data.repository.SatelliteRepository
import com.example.satellitesapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatelliteDetailViewModel @Inject constructor(
    private val repository: SatelliteRepository
) : ViewModel() {

    private val _satelliteDetailAndPosition =
        MutableLiveData<Resource<Pair<SatelliteDetailData, List<PositionData?>>>>()
    val satelliteDetailAndPosition: LiveData<Resource<Pair<SatelliteDetailData, List<PositionData?>>>> =
        _satelliteDetailAndPosition

    fun getSatelliteDetailAndPosition(satelliteId: Int) {
        viewModelScope.launch {
            _satelliteDetailAndPosition.value = Resource.Loading
            delay(1000)

            try {
                val detail = repository.getSatelliteDetail(satelliteId)
                val positions = repository.getSatellitePosition(satelliteId)

                if (detail is Resource.Error || positions is Resource.Error) {
                    _satelliteDetailAndPosition.value = Resource.Error("Bir hata oluştu.")
                } else if (detail is Resource.Success && positions is Resource.Success
                    && detail.data != null && positions.data.isNotEmpty()
                ) {
                    _satelliteDetailAndPosition.value =
                        Resource.Success(Pair(detail.data, positions.data))
                }
            } catch (e: Exception) {
                _satelliteDetailAndPosition.value = Resource.Error("Bir hata oluştu: ${e.message}")
            }
        }
    }
}