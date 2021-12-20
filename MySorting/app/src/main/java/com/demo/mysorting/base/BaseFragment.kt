package com.demo.mysorting.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.demo.mysorting.delegate.OnActivityDelegate

open class BaseFragment : Fragment() {

    private var delegate: OnActivityDelegate? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        delegate = try {
            if(context is OnActivityDelegate) context else null
        } catch (e: Exception) {
            null
        }
    }

    open fun getActivityDelegate() = delegate
    open fun onFragmentReceiveData(bundle: Bundle?) = Unit

}