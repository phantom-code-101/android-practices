package com.demo.customnavigator.demo

import com.demo.core.base.BaseFragment
import com.demo.customnavigator.R
import com.demo.customnavigator.databinding.FragmentDemoBinding

class DemoFragment: BaseFragment<FragmentDemoBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_demo

}