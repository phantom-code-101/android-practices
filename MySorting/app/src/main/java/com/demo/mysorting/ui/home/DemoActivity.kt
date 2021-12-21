package com.demo.mysorting.ui.home

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import com.demo.mysorting.R
import com.demo.mysorting.base.BaseActivity
import com.demo.mysorting.database.CurrencyInfo
import com.demo.mysorting.databinding.ActivityMainBinding
import com.demo.mysorting.delegate.OnActivityDelegate
import com.demo.mysorting.extensions.jsonFromAsset
import com.demo.mysorting.extensions.safeOnClickListener
import com.demo.mysorting.model.Result
import com.demo.mysorting.ui.currency.CurrencyListFragmentDirections
import com.demo.mysorting.utils.PageConstants
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import org.koin.android.ext.android.get
import java.util.concurrent.TimeUnit

class DemoActivity : BaseActivity(), OnActivityDelegate {

    private lateinit var binding: ActivityMainBinding
    private val sortButtonAnimatorSet = AnimatorSet()
    private val sortPublish: PublishSubject<String> = PublishSubject.create()
    private var sortByToggle = false

    private val homeViewModel: HomeViewModel by viewModels {
        get<HomeModelFactory>()
    }

    override fun getNavControllerLayoutId(): Int = R.id.main_content

    override fun getNavGraphId(): Int = R.navigation.main_graph

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewmodel = homeViewModel
        setContentView(binding.root)
        setDatabase()
        setListener()
        setAnimation()
        setSortingPublish()
    }

    private fun setDatabase() {
        homeViewModel.setup(
            jsonFromAsset("currency_list.json", object : TypeToken<Array<CurrencyInfo>>() {}.type)
        )
    }

    private fun setListener() {
        homeViewModel.currencyInfoList.observe(this) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.isLoading = true
                }
                is Result.Success<Array<CurrencyInfo>> -> {
                    binding.isLoading = false
                    sortButtonAnimatorSet.start()

                    val action = CurrencyListFragmentDirections.currencyListFragment(currencyList = result.data)
                    getNavController()?.navigate(action)

                }
                is Result.Failure -> {
                    binding.isLoading = false
                }
                else -> {}
            }
        }

        binding.btnLoadData.safeOnClickListener {
            homeViewModel.getCurrencyList()
            binding.btnLoadData.visibility = View.GONE
        }

        binding.btnSorting.setOnClickListener {
            val sortBy = if (sortByToggle) PageConstants.Sorting.BY_NAME else PageConstants.Sorting.BY_LENGTH
            sortPublish.onNext(sortBy)
        }
    }

    private fun setSortingPublish() {
        sortPublish
            .debounce(200, TimeUnit.MILLISECONDS)
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe { sortBy ->
                dispatchToFragment(
                    R.id.currencyListFragment, bundleOf(
                        PageConstants.Page.SORTING to sortBy
                    )
                )

                sortByToggle = !sortByToggle
            }
    }

    private fun setAnimation() {
        val fadeInAnimator = ObjectAnimator.ofFloat(binding.btnSorting, "alpha", 0f, 1f).apply {
            duration = 500
        }

        sortButtonAnimatorSet.play(fadeInAnimator)
    }

    override fun onPageAction(data: Map<String, Any>) {
        when (data[PageConstants.Page.ACTION]) {
            PageConstants.Action.CURRENCY_LIST_ITEM_CLICK -> {
                Toast.makeText(this, "${data["coinId"]} Clicked!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        homeViewModel.stop()
    }
}