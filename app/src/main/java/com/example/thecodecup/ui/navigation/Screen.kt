package com.example.thecodecup.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Details : Screen("details/{coffeeId}") {
        fun createRoute(coffeeId: String) = "details/$coffeeId"
    }
    object Cart : Screen("cart")
    object OrderSuccess : Screen("order_success")
    object Profile : Screen("profile")
    object Rewards : Screen("rewards")
    object RedeemRewards : Screen("redeem_rewards")
    object MyOrder : Screen("my_order")
}
