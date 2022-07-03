package to.boosty.cmit.unitylesson.ui.theme.util

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import to.boosty.cmit.unitylesson.ui.theme.chooseCity.ChooseCityScreen
import to.boosty.cmit.unitylesson.ui.theme.weatherCity.WeatherScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.ChooseCityScreen.route) {

        composable(Screen.ChooseCityScreen.route) {
            ChooseCityScreen(navController)
        }

        composable(Screen.WeatherScreen.route + "{city}") { navBackStackEntry ->
            val city = navBackStackEntry.arguments?.getString("city") ?: "New York"
            WeatherScreen(city = city)
        }
    }
}