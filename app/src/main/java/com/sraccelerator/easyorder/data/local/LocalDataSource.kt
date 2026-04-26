package com.sraccelerator.easyorder.data.local

import com.sraccelerator.easyorder.data.model.Category
import com.sraccelerator.easyorder.data.model.Product

interface LocalDataSource<T> {
    suspend fun save(items: List<T>)
    suspend fun getAll(): List<T>
    suspend fun clearAll()
}

interface CategoryLocalDataSource : LocalDataSource<Category>

interface ProductLocalDataSource : LocalDataSource<Product> {
    suspend fun getByCategoryId(categoryId: Int): List<Product>
}
