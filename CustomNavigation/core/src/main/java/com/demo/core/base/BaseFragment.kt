package com.demo.core.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

open class BaseFragment<V : ViewDataBinding> : Fragment() {

    private val TAG = javaClass.simpleName
    private var _binding: V? = null
    val binding: V get() = _binding!!

    open fun getLayoutId(): Int = -1

    open fun onCreateDataBinding(viewDataBinding: V) {
        _binding = viewDataBinding
        _binding?.lifecycleOwner = viewLifecycleOwner
    }

    open fun onDisappear() {
        Log.i(TAG, "$TAG onDisappear")
    }

    open fun onWillAppear() {
        Log.i(TAG, "$TAG onWillAppear")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            onDisappear()
        } else {
            onWillAppear()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (getLayoutId() > 0) {
            onCreateDataBinding(DataBindingUtil.inflate(layoutInflater, getLayoutId(), container, false))
            binding.root
        } else {
            super.onCreateView(inflater, container, savedInstanceState)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}