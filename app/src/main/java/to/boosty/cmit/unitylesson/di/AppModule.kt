package to.boosty.cmit.unitylesson.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import to.boosty.cmit.unitylesson.data.WeatherApi
import to.boosty.cmit.unitylesson.repository.WeatherRepository
import to.boosty.cmit.unitylesson.util.Constants
import to.boosty.cmit.unitylesson.util.Constants.BASE_URL
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideWeatherApi(
    ): WeatherApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)


    @Singleton
    @Provides
    fun provideWeatherRepository(weatherApi: WeatherApi) = WeatherRepository(weatherApi)
}