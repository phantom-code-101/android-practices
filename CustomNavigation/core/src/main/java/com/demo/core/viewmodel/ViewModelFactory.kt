package com.demo.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.core.network.DataSourceImpl

class ViewModelFactory {

    companion object {

        fun <D: DataSourceImpl> create(dataSource: D): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(dataSource::class.java)) {
                        return dataSource as T
                    }

                    throw IllegalArgumentException("unexpected model class $modelClass")
                }
            }
        }

    }
}