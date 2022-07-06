package com.demo.core.navigation

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.plusAssign

class PersistentNavHostFragment : NavHostFragment() {

    override fun onCreateNavController(navController: NavController) {
        super.onCreateNavController(navController)

        // add one more Navigator into graph.xml
        val navigator = PersistentNavigator(requireContext(), childFragmentManager, id)
        navController.navigatorProvider.plusAssign(navigator)
    }

}