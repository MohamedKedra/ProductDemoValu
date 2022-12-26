package com.example.productdemovalu.view.list

import androidx.lifecycle.viewModelScope
import com.example.productdemovalu.remote.Product
import com.example.productdemovalu.repository.ProductRepository
import com.example.productdemovalu.utils.BaseViewModel
import com.example.productdemovalu.utils.ConnectionManager
import com.example.productdemovalu.utils.LiveDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: ProductRepository,
    private val connectionManager: ConnectionManager
) : BaseViewModel() {

    private val productData = LiveDataState<List<Product>>()

    fun refreshProducts(): LiveDataState<List<Product>> {

        publishLoading(productData)

        if (!connectionManager.isNetworkAvailable) {
            publishNoInternet(productData)
            return productData
        }

        viewModelScope.launch {

            val result = repository.getAllProducts()
            if (result.isSuccessful) {
                publishResult(productData, result.body())
            } else {
                publishError(productData, result.message())
            }
        }

        return productData
    }
}