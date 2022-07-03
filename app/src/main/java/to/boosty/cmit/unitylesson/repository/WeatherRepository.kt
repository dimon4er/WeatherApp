package to.boosty.cmit.unitylesson.repository

import to.boosty.cmit.unitylesson.util.Resource
import to.boosty.cmit.unitylesson.data.CityWeather
import to.boosty.cmit.unitylesson.data.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherApi: WeatherApi) {

    suspend fun getWeatherForCity(city: String): Resource<CityWeather> {
        return try {
            val response = weatherApi.getWeatherForCity(city = city)
            if (response.isSuccessful) {
                Resource.success(response.body())
            } else {
                Resource.error(response.message(), data = null)
            }
        } catch (e: Exception) {
            Resource.error("Couldn't connect to the service. Check your internet connection", null)
        }
    }
}