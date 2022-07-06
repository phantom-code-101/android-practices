package com.demo.feed.ui.hackernews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.demo.feed.databinding.FragmentHackerNewsListBinding

class HackerNewsListFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentHackerNewsListBinding.inflate(inflater).root
    }

}