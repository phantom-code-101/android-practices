package com.demo.mysorting.delegate

import android.os.Bundle
import androidx.annotation.IdRes

interface OnActivityDelegate {
    fun onPageAction(data: Map<String, Any>)
    fun dispatchToFragment(@IdRes id: Int, bundle: Bundle?)
    fun getOnFragmentDelegateById(@IdRes id: Int): OnFragmentDelegate?
}