package com.bangkit.smeus.ui.user

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bangkit.smeus.ui.navigation.NavigationItem
import com.bangkit.smeus.ui.navigation.Screen
import com.bangkit.smeus.ui.screen.DetailScreen
import com.bangkit.smeus.ui.screen.ExploreScreen
import com.bangkit.smeus.ui.screen.FavoriteScreen
import com.bangkit.smeus.ui.screen.ProfileScreen
import com.bangkit.smeus.ui.screen.SearchScreen
import com.bangkit.smeus.ui.theme.SMEUsTheme

class UserActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SMEUsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    User()
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun User(
    modifier: Modifier = Modifier
){
    val navController: NavHostController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController
            )
        }
    ) {innerPadding ->
        Box(
            modifier = modifier.padding(innerPadding)
        ){
            NavHost(
                navController = navController,
                startDestination = Screen.Explore.route,
                modifier = modifier
            ){
                composable(Screen.Explore.route){
                    ExploreScreen(
                        navigateToDetail = {smeId ->
                            navController.navigate(Screen.Detail.createRoute(smeId))
                        }
                    )
                }
                composable(Screen.Search.route){
                    SearchScreen()
                }
                composable(Screen.Favorite.route){
                    FavoriteScreen()
                }
                composable(Screen.Profile.route){
                    ProfileScreen()
                }
                composable(
                    route = Screen.Detail.route,
                    arguments = listOf(navArgument("smeId") {type = NavType.StringType})
                ){
                    val id = it.arguments?.getString("smeId") ?: ""
                    DetailScreen(
                        smeId = id,
                        navigateBack = {
                            navController.navigateUp()
                        },
                        navigateToDetail = {smeId ->
                            navController.navigate(Screen.Detail.createRoute(smeId))
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavController,
    modifier: Modifier = Modifier
){
    BottomNavigation(
        modifier = modifier,
        contentColor = Color.White,
        backgroundColor = Color.Red
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = "Explore",
                icon = Icons.Default.Search,
                screen = Screen.Explore
            ),
            NavigationItem(
                title = "Favorite",
                icon = Icons.Default.Favorite,
                screen = Screen.Favorite
            ),
            NavigationItem(
                title = "Profile",
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            )
        )
        BottomNavigation {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    label = { Text(text = item.title) },
                    selected = currentRoute == item.screen.route,
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.Gray,
                    onClick = {
                        navController.navigate(item.screen.route){
                            popUpTo(navController.graph.findStartDestination().id){
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserPreview() {
    SMEUsTheme() {
        User()
    }
}