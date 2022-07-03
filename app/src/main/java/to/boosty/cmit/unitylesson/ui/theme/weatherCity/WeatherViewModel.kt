package to.boosty.cmit.unitylesson.ui.theme.weatherCity

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import to.boosty.cmit.unitylesson.data.CityWeather
import to.boosty.cmit.unitylesson.repository.WeatherRepository
import to.boosty.cmit.unitylesson.util.Resource
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    private val _cityWeather = mutableStateOf<Resource<CityWeather>>(Resource.loading(null))
    var cityWeather :State<Resource<CityWeather>> = _cityWeather

    private var city: String = ""


    fun getWeather() {
        viewModelScope.launch {
            _cityWeather.value = weatherRepository.getWeatherForCity(city = city)
        }
    }

    fun submitCity(cityName:String) {
        city = cityName
    }
}