package com.demo.mysorting.extensions

import android.os.SystemClock
import android.view.View

fun View.safeOnClickListener(callback: (() -> Unit)?) {
    setOnClickListener(object : View.OnClickListener {

        private var lastClickTime = 0L

        override fun onClick(v: View?) {
            if (SystemClock.elapsedRealtime() - lastClickTime < 1000) {
                return
            }
            callback?.invoke()
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}