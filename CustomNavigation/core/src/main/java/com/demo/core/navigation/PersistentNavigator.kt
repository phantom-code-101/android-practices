package com.demo.core.navigation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator
import java.util.ArrayDeque

@Navigator.Name("PersistentFragment")
class PersistentNavigator(
    private val context: Context,
    private val fragmentManager: FragmentManager,
    private val containerId: Int
) : FragmentNavigator(context, fragmentManager, containerId) {

    private val TAG = "PersistentFragment"
    private val KEY_BACK_STACK_IDS = "androidx-nav-fragment:navigator:backStackIds"
    private val backStack = ArrayDeque<Int>()

    override fun navigate(
        destination: Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Navigator.Extras?
    ): NavDestination? {
        if (fragmentManager.isStateSaved) {
            Log.i(TAG, "Ignoring navigate() call: FragmentManager has already saved its state")
            return null
        }

        var className = destination.className
        if (className[0] == '.') {
            className = context.packageName + className
        }

        val ft = fragmentManager.beginTransaction()

        var enterAnim = navOptions?.enterAnim ?: -1
        var exitAnim = navOptions?.exitAnim ?: -1
        var popEnterAnim = navOptions?.popEnterAnim ?: -1
        var popExitAnim = navOptions?.popExitAnim ?: -1
        if (enterAnim != -1 || exitAnim != -1 || popEnterAnim != -1 || popExitAnim != -1) {
            enterAnim = if (enterAnim != -1) enterAnim else 0
            exitAnim = if (exitAnim != -1) exitAnim else 0
            popEnterAnim = if (popEnterAnim != -1) popEnterAnim else 0
            popExitAnim = if (popExitAnim != -1) popExitAnim else 0
            ft.setCustomAnimations(enterAnim, exitAnim, popEnterAnim, popExitAnim)
        }

        val tag = destination.id.toString()
        val primaryNavigationFragment = fragmentManager.primaryNavigationFragment
        var destinationFragment = fragmentManager.findFragmentByTag(tag)

        if (primaryNavigationFragment != null) {
            Log.i(TAG, "primaryNavigationFragment hidden")
            ft.hide(primaryNavigationFragment)
        }

        if (destinationFragment == null) {
            destinationFragment = fragmentManager.fragmentFactory.instantiate(
                context.classLoader, className
            )

            destinationFragment.arguments = args
            ft.add(containerId, destinationFragment, tag)
            Log.i(TAG, "Destination $tag Added.")
        } else {
            Log.i(TAG, "Show destination $tag")
            ft.show(destinationFragment)
        }

        ft.setPrimaryNavigationFragment(destinationFragment)

        @IdRes val destId = destination.id
        val initialNavigation = backStack.isEmpty()
        // TODO Build first class singleTop behavior for fragments
        val isSingleTopReplacement = (navOptions != null && !initialNavigation
            && navOptions.shouldLaunchSingleTop()
            && backStack.peekLast() == destId)

        var isAdded: Boolean
        if (initialNavigation) {
            isAdded = true
        } else if (isSingleTopReplacement) {
            // Single Top means we only want one instance on the back stack
            if (backStack.size > 1) {

                Log.i(TAG, "Destination SingleTop : pop fragment without $tag")
                // If the Fragment to be replaced is on the FragmentManager's
                // back stack, a simple replace() isn't enough so we
                // remove it from the back stack and put our replacement
                // on the back stack in its place
                fragmentManager.popBackStack(
                    createBackStackName(backStack.size, backStack.peekLast()),
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                )
                ft.addToBackStack(createBackStackName(backStack.size, destId))
            }
            isAdded = false
        } else {
            ft.addToBackStack(createBackStackName(backStack.size + 1, destId))
            isAdded = true
        }

        if (navigatorExtras is Extras) {
            navigatorExtras.sharedElements.onEachIndexed { index, entry ->
                ft.addSharedElement(entry.key, entry.value)
            }
        }

        ft.apply {
            setReorderingAllowed(true)
            commit()
            Log.i(TAG, "FragmentManager.beginTransaction commit $tag that's reordering allowed.")
        }

        // The commit succeeded, update our view of the world
        return if (isAdded) {
            backStack.add(destId)
            destination
        } else {
            null
        }
    }

    override fun popBackStack(): Boolean {
        if (backStack.isEmpty()) {
            return false
        }

        if (fragmentManager.isStateSaved) {
            Log.i(TAG, "Ignoring popBackStack() call: FragmentManager has already saved its state")
            return false
        }

        Log.i(TAG, "popBackStack the last element from arrays of the backStack.")

        fragmentManager.popBackStack(
            createBackStackName(backStack.size, backStack.peekLast()),
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
        backStack.removeLast()
        return true
    }

    override fun onSaveState(): Bundle {
        val backStackIds = IntArray(backStack.size)
        var index = 0
        backStack.forEach { id ->
            backStackIds[index++] = id
        }

        return Bundle().apply {
            putIntArray(KEY_BACK_STACK_IDS, backStackIds)
        }
    }

    override fun onRestoreState(savedState: Bundle?) {
        savedState?.also {
            savedState.getIntArray(KEY_BACK_STACK_IDS)?.also { backStackIds ->
                backStack.clear()
                backStackIds.forEach { destId ->
                    backStack.add(destId)
                }
            }
        }
    }

    override fun createDestination() = Destination(this)

    private fun createBackStackName(backStackIndex: Int, destId: Int?): String? {
        return "$backStackIndex-$destId"
    }

}