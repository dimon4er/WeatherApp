package to.boosty.cmit.unitylesson.ui.theme.weatherCity

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import to.boosty.cmit.unitylesson.R
import to.boosty.cmit.unitylesson.util.Status

@Composable
fun WeatherScreen(
    city: String,
    viewModel: WeatherViewModel = hiltViewModel()
) {


    LaunchedEffect(key1 = city) {
        viewModel.submitCity(city)
        viewModel.getWeather()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())

    ) {
        when (viewModel.cityWeather.value.status) {
            Status.SUCCESS -> {

                Text(text = viewModel.cityWeather.value.data?.name ?: city, fontSize = 48.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)

                Image(
                    painter = painterResource(
                        id =
                        when (viewModel.cityWeather.value.data?.weather?.first()?.icon) {
                            "01d" -> R.drawable.weather01d
                            "01n" -> R.drawable.weather01n
                            "02d" -> R.drawable.weather02d
                            "02n" -> R.drawable.weather02n
                            "03d", "03n" -> R.drawable.weather03
                            "04d", "04n" -> R.drawable.weather04
                            "09d", "09n" -> R.drawable.weather09
                            "10d" -> R.drawable.weather10d
                            "10n" -> R.drawable.weather10n
                            "11d", "11n" -> R.drawable.weather11
                            "13d", "13n" -> R.drawable.weather13
                            "50d", "50n" -> R.drawable.weather50
                            else -> R.drawable.weather01d
                        }
                    ), contentDescription = "",
                    modifier = Modifier.size(256.dp)
                )

                Text(
                    text = "${viewModel.cityWeather.value.data?.weather?.first()?.description}",
                    fontSize = 32.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center
                )

                Text(text = "${viewModel.cityWeather.value.data?.main?.temp}°", fontSize = 48.sp)

                Text(
                    buildAnnotatedString {

                        withStyle(style = SpanStyle(color = Color.Blue)) {
                            append("${viewModel.cityWeather.value.data?.main?.temp_min}°")
                        }
                        append(" | ")
                        withStyle(
                            style = SpanStyle(
                                color = Color.Red
                            )
                        ) {
                            append("${viewModel.cityWeather.value.data?.main?.temp_max}°")
                        }
                    }, fontSize = 32.sp
                )

                Text(
                    text = "Влажность: ${viewModel.cityWeather.value.data?.main?.humidity}%",
                    fontSize = 32.sp
                )

            }

            Status.ERROR -> {
                Text(
                    text = viewModel.cityWeather.value.message ?: "Неизвестная ошибка",
                    fontSize = 16.sp
                )
            }
            Status.LOADING -> {
                CircularProgressIndicator()
            }
        }
    }
}