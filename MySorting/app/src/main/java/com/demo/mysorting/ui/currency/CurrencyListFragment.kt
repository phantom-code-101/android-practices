package com.demo.mysorting.ui.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.mysorting.base.BaseFragment
import com.demo.mysorting.databinding.CurrencyListFragmentBinding
import com.demo.mysorting.delegate.OnFragmentDelegate
import com.demo.mysorting.utils.PageConstants
import org.koin.android.ext.android.inject

class CurrencyListFragment : BaseFragment(), OnFragmentDelegate {

    private val currencyListViewModel: CurrencyListViewModel by inject()

    private val args: CurrencyListFragmentArgs by navArgs()
    private var _binding: CurrencyListFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var currencyListAdapter: CurrencyListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CurrencyListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayout()
        setListener()
    }

    private fun setListener() {
        currencyListViewModel.currencyInfoList.observe(viewLifecycleOwner) {
            currencyListAdapter.submitList(it) {
                binding.rvCurrencyList.scrollToPosition(0)
            }
        }

        binding.rvCurrencyList.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            private var scrollDy = 0
            private var scrollDx = 0

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                getScrollingDelegate()?.onScrollingInfo(dx, dy, scrollDx, scrollDy)
                scrollDx = dx
                scrollDy = dy
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                binding.searchContainer.isSelected = scrollDy > 0
                getScrollingDelegate()?.onScrollingState(newState)
            }
        })
    }

    private fun setLayout() {
        binding.container.visibility = if (args.currencyList?.isNotEmpty() == true) {
            View.VISIBLE
        } else {
            View.GONE
        }

        currencyListAdapter = CurrencyListAdapter().apply {
            submitList(args.currencyList?.toList())
            setCurrencyItemClick { currencyInfo ->
                getActivityDelegate()?.onPageAction(
                    mapOf(
                        PageConstants.Page.ACTION to PageConstants.Action.CURRENCY_LIST_ITEM_CLICK,
                        "coinId" to currencyInfo.id
                    )
                )
            }
        }

        binding.rvCurrencyList.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvCurrencyList.adapter = currencyListAdapter
    }

    override fun onFragmentReceiveData(bundle: Bundle?) {
        super.onFragmentReceiveData(bundle)
        val sortBy = bundle?.getString(PageConstants.Page.SORTING) ?: PageConstants.Sorting.BY_DEFAULT
        args.currencyList?.toList()?.also { currencyListViewModel.sorting(sortBy, it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        currencyListViewModel.stop()
        _binding = null
    }
}