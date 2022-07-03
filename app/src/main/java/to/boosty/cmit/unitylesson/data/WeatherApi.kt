package to.boosty.cmit.unitylesson.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import to.boosty.cmit.unitylesson.util.Constants

interface WeatherApi {

    @GET("weather")
    suspend fun getWeatherForCity(
        @Query("q") city: String,
        @Query("appid") apiKey: String = Constants.API_KEY,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "ru",
    ) :Response<CityWeather>
}