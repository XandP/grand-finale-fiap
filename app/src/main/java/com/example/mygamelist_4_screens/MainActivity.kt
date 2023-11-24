package com.example.mygamelist_4_screens



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mygamelist_4_screens.ui.theme.MyGameList_4_screensTheme
import com.example.mygamelist_4_screens.repository.Login
import com.example.mygamelist_4_screens.repository.Home
import com.example.mygamelist_4_screens.repository.GameInfo
import com.example.mygamelist_4_screens.repository.GameReview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyGameList_4_screensTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val gameIds = listOf(
                        730, 1086940, 374320, 271590, 1716740, 105600, 1091500, 289070, 1971870
                    )

                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "Login"
                    ) {
                        composable(route = "Login") { Login(navController) }
                        composable(route = "Home") { Home(navController) }
                        composable(
                            route = "GameInfo?gameName={gameName}&gameDesc={gameDesc}&gameImage={gameImage}",
                            arguments = listOf(
                                navArgument("gameName") { defaultValue = "" },
                                navArgument("gameDesc") { defaultValue = "" },
                                navArgument("gameImage") { defaultValue = "" }
                            )
                        ) { backStackEntry ->
                            val gameName = backStackEntry.arguments?.getString("gameName") ?: ""
                            val gameDesc = backStackEntry.arguments?.getString("gameDesc") ?: ""
                            val gameImage = backStackEntry.arguments?.getString("gameImage") ?: ""

                            GameInfo(navController, gameName, gameDesc, gameImage)
                        }
                        composable(
                            route = "GameReview?gameName={gameName}&gameDesc={gameDesc}&gameImage={gameImage}",
                            arguments = listOf(
                                navArgument("gameName") { defaultValue = "" },
                                navArgument("gameDesc") { defaultValue = "" },
                                navArgument("gameImage") { defaultValue = "" }
                            )
                        ) { backStackEntry ->
                            val gameName = backStackEntry.arguments?.getString("gameName") ?: ""
                            val gameDesc = backStackEntry.arguments?.getString("gameDesc") ?: ""
                            val gameImage = backStackEntry.arguments?.getString("gameImage") ?: ""

                            GameReview(navController, gameName, gameDesc, gameImage)
                        }
                    }
                }
            }
        }
    }
}
