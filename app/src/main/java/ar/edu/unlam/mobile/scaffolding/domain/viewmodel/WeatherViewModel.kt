package ar.edu.unlam.mobile.scaffolding.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.model.WeatherResponse
import ar.edu.unlam.mobile.scaffolding.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel
    @Inject
    constructor(
        private val repository: WeatherRepository,
    ) : ViewModel() {
        private val _currentWeather = MutableLiveData<WeatherResponse>()
        val currentWeather: LiveData<WeatherResponse> get() = _currentWeather

        fun fetchCurrentWeather() {
            viewModelScope.launch {
                val response =
                    repository.getCurrentWeather("-34.669635", "-58.564624", "3dc2572c03618c71960037d86b24c7d2")
                _currentWeather.value = response
            }
        }
    }

class WeatherViewModelFactory(
    private val repository: WeatherRepository,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
