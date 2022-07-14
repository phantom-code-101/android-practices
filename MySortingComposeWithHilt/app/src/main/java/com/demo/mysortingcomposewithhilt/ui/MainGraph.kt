package com.demo.mysortingcomposewithhilt.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.demo.mysortingcomposewithhilt.database.CurrencyInfo
import com.demo.mysortingcomposewithhilt.ui.currency.CurrencyListScreen
import com.demo.mysortingcomposewithhilt.ui.currency.CurrencyListViewModel
import com.demo.mysortingcomposewithhilt.ui.home.HomeScreen
import com.demo.mysortingcomposewithhilt.ui.home.HomeViewModel
import com.demo.mysortingcomposewithhilt.utils.PageConstants

@Composable
fun MainGraph() {
    val navController = rememberNavController()
    val homeViewModel: HomeViewModel = viewModel()
    val currencyListViewModel: CurrencyListViewModel = viewModel()

    NavHost(navController = navController, startDestination = PageConstants.ROUTE_HOME) {
        composable(route = PageConstants.ROUTE_HOME) {
            HomeScreen()
        }
        composable(route = PageConstants.ROUTE_CURRENCY_LIST) { entry ->
            val currencyList = navController.previousBackStackEntry?.arguments?.getParcelableArray("currency_list") as? Array<CurrencyInfo>
            CurrencyListScreen(currencyList?.toList())
        }
    }
}