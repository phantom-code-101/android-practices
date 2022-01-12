package com.demo.mysorting.ui.currency

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.mysorting.database.CurrencyInfo
import com.demo.mysorting.databinding.RowCurrencyListItemBinding
import com.demo.mysorting.extensions.safeOnClickListener

internal class CurrencyListAdapter : ListAdapter<CurrencyInfo, CurrencyItem>(DIFF_CALLBACK) {

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CurrencyInfo>() {
            override fun areItemsTheSame(oldItem: CurrencyInfo, newItem: CurrencyInfo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CurrencyInfo, newItem: CurrencyInfo): Boolean {
                return oldItem == newItem
            }
        }

    }

    private val viewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()
    private var clickCallback: ((CurrencyInfo) -> Unit)? = null

    fun setCurrencyItemClick(callback: ((CurrencyInfo) -> Unit)?) {
        this.clickCallback = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyItem {
        val view = viewPool.getRecycledView(viewType) ?: run {
            val itemBinding = CurrencyItem(
                RowCurrencyListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )

            viewPool.putRecycledView(itemBinding)
            itemBinding
        }

        return view as CurrencyItem
    }

    override fun onBindViewHolder(holder: CurrencyItem, position: Int) {
        val currencyInfo = getItem(position)
        holder.bindTo(currencyInfo)
        holder.itemView.safeOnClickListener {
            clickCallback?.invoke(currencyInfo)
        }
    }

}

internal class CurrencyItem(
    private val binding: RowCurrencyListItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bindTo(currencyInfo: CurrencyInfo) {
        binding.setCoinId(currencyInfo.id.substring(0, 1))
        binding.setCoinName(currencyInfo.name)
        binding.setCoinSymbol(currencyInfo.symbol)
    }
}