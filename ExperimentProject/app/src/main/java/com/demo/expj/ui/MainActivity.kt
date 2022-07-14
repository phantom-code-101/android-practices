package com.demo.expj.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.demo.expj.HeightTopWindowInsetsListener
import com.demo.expj.NavigationHost
import com.demo.expj.R
import com.demo.expj.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity: AppCompatActivity(), NavigationHost {

    companion object {
        /** Key for an int extra defining the initial navigation target. */
        const val EXTRA_NAVIGATION_ID = "extra.NAVIGATION_ID"
        private const val NAV_ID_NONE = -1

    }

    private val viewModel: MainViewModel by viewModels()

//    private val TOP_LEVEL_DESTINATIONS = setOf(
//        -1
//    )

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private var currentNavId = NAV_ID_NONE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.statusBarScrim.setOnApplyWindowInsetsListener(HeightTopWindowInsetsListener)

        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            currentNavId = destination.id
            // TODO: hide nav if not a top-level destination?
        }

        if (savedInstanceState == null) {
            currentNavId = navController.graph.startDestinationId
            val requestedNavId = intent.getIntExtra(EXTRA_NAVIGATION_ID, currentNavId)
            navigateTo(requestedNavId)
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.navigationActions.collect { action ->

                }
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(binding.rootContainer) { view, insets ->
            // Hide the bottom navigation view whenever the keyboard is visible.
            val imeVisible = insets.isVisible(WindowInsetsCompat.Type.ime())
            // binding.bottomNavigation?.isVisible = !imeVisible

            // If we're showing the bottom navigation, add bottom padding. Also, add left and right
            // padding since there's no better we can do with horizontal insets.
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val bottomPadding = 0
            //val bottomPadding = if (binding.bottomNavigation?.isVisible == true) {
            //    systemBars.bottom
            //} else 0
            view.updatePadding(
                left = systemBars.left,
                right = systemBars.right,
                bottom = bottomPadding
            )
            // Consume the insets we've used.
            WindowInsetsCompat.Builder(insets).setInsets(
                WindowInsetsCompat.Type.systemBars(),
                Insets.of(
                    0, systemBars.top, 0, systemBars.bottom - bottomPadding)
            ).build()
        }
    }

    override fun registerToolbarWithNavigation(toolbar: Toolbar) {
//        val appBarConfiguration = AppBarConfiguration(TOP_LEVEL_DESTINATIONS)
//        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun navigateTo(navId: Int) {
        if (navId == currentNavId) {
            return // user tapped the current item
        }
        navController.navigate(navId)
    }
}