package com.sraccelerator.easyorder.data.local

import com.sraccelerator.easyorder.data.model.Category
import com.sraccelerator.easyorder.data.model.Product

sealed class QueryCriteria {
    data class Equals(val field: String, val value: Any) : QueryCriteria()
    data class GreaterThan(val field: String, val value: Number) : QueryCriteria()
    data class InList(val field: String, val values: List<Any>) : QueryCriteria()
}

interface LocalDataSource<T> {
    suspend fun save(items: List<T>)
    suspend fun getAll(): List<T>
    suspend fun getBy(vararg criteria: QueryCriteria): List<T>
    suspend fun clearAll()
}

interface CategoryLocalDataSource : LocalDataSource<Category>
interface ProductLocalDataSource : LocalDataSource<Product>
