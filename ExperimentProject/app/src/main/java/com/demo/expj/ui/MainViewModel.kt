package com.demo.expj.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class MainViewModel : ViewModel() {

    private val _navigationActions = Channel<MainNavigationAction>(Channel.CONFLATED)
    val navigationActions = _navigationActions.receiveAsFlow()

}


sealed class MainNavigationAction {

}
