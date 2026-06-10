package com.example.thecodecup.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.thecodecup.OpeningScreen

import com.example.thecodecup.ui.screens.home.HomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            OpeningScreen(onFinished = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            })
        }
        
        composable(Screen.Home.route) {
            HomeScreen(
                onCoffeeClick = { coffee ->
                    navController.navigate(Screen.Details.createRoute(coffee.id))
                },
                onCartClick = {
                    navController.navigate(Screen.Cart.route)
                },
                onProfileClick = {
                    navController.navigate(Screen.Profile.route)
                }
            )
        }
        
        composable(Screen.Details.route) { backStackEntry ->
            val coffeeId = backStackEntry.arguments?.getString("coffeeId")
            PlaceholderScreen(title = "Details Screen: $coffeeId")
        }
        
        composable(Screen.Cart.route) {
            PlaceholderScreen(title = "Cart Screen")
        }
        
        composable(Screen.OrderSuccess.route) {
            PlaceholderScreen(title = "Order Success Screen")
        }
        
        composable(Screen.Profile.route) {
            PlaceholderScreen(title = "Profile Screen")
        }
        
        composable(Screen.Rewards.route) {
            PlaceholderScreen(title = "Rewards Screen")
        }
    }
}

@Composable
fun PlaceholderScreen(title: String) {
    androidx.compose.material3.Text(text = title)
}
