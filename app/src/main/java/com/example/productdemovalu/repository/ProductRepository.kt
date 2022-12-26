package com.example.productdemovalu.repository

import com.example.productdemovalu.remote.ProductService
import javax.inject.Inject

class ProductRepository @Inject constructor(private val service: ProductService) {

    suspend fun getAllProducts() = service.getAllProducts()
}