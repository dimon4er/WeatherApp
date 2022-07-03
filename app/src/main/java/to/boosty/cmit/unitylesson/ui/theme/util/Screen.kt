package to.boosty.cmit.unitylesson.ui.theme.util

sealed class Screen(val route: String) {
    object ChooseCityScreen : Screen ("choose_city_screen")
    object WeatherScreen : Screen ("weather_city_screen/")
}
