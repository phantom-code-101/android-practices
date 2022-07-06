package com.demo.core.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.navigation.findNavController

open class BaseActivity<V : ViewDataBinding> : AppCompatActivity() {

    private val TAG = javaClass.simpleName
    private var _binding: V? = null
    val binding: V get() = _binding!!

    open fun getLayoutId(): Int = -1

    open fun onCreateDataBinding(viewDataBinding: V) {
        _binding = viewDataBinding
        _binding?.lifecycleOwner = this
    }

    open fun getNavControllerLayoutId(): Int = -1

    open fun getNavController(): NavController? {
        return getNavControllerLayoutId().takeIf { it > -1 }?.let {
            findNavController(getNavControllerLayoutId())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getLayoutId() > 0) {
            onCreateDataBinding(DataBindingUtil.setContentView(this, getLayoutId()))
            setContentView(binding.root)
        } else {
            setContentView(View(this))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}