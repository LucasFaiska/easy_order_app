package com.sraccelerator.easyorder.data.local

import com.sraccelerator.easyorder.data.model.Category
import com.sraccelerator.easyorder.data.model.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryCategoryLocalDataSource @Inject constructor() : CategoryLocalDataSource {
    private var cache: List<Category> = emptyList()

    override suspend fun save(items: List<Category>) {
        cache = items
    }

    override suspend fun getAll(): List<Category> {
        return cache
    }

    override suspend fun clearAll() {
        cache = emptyList()
    }
}

@Singleton
class InMemoryProductLocalDataSource @Inject constructor() : ProductLocalDataSource {
    private var cache: List<Product> = emptyList()

    override suspend fun save(items: List<Product>) {
        cache = items
    }

    override suspend fun getAll(): List<Product> {
        return cache
    }

    override suspend fun getByCategoryId(categoryId: Int): List<Product> {
        return cache
    }

    override suspend fun clearAll() {
        cache = emptyList()
    }
}
