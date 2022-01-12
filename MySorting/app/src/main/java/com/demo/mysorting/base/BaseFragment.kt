package com.demo.mysorting.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.demo.mysorting.delegate.OnActivityDelegate
import com.demo.mysorting.delegate.OnScrollingDelegate

open class BaseFragment : Fragment() {

    private var activityDelegate: OnActivityDelegate? = null
    private var scrollingDelegate: OnScrollingDelegate? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityDelegate = try {
            if (context is OnActivityDelegate) context else null
        } catch (e: Exception) {
            null
        }

        scrollingDelegate = try {
            if (context is OnScrollingDelegate) context else null
        } catch (e: Exception) {
            null
        }
    }

    open fun getActivityDelegate() = activityDelegate
    open fun getScrollingDelegate() = scrollingDelegate
    open fun onFragmentReceiveData(bundle: Bundle?) = Unit

}