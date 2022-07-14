package com.demo.mysortingcomposewithhilt.ui.home

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen() {

    val homeViewModel: HomeViewModel = viewModel()

    Button(onClick = {
        homeViewModel.getCurrencyList()
    }) {
        Text(text = "Go")
    }

}