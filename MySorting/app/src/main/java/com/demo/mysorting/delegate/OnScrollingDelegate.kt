package com.demo.mysorting.delegate

interface OnScrollingDelegate {

    fun onScrollingInfo(dx: Int, dy: Int, oldDx: Int, oldDy: Int)
    fun onScrollingState(newState: Int)

}