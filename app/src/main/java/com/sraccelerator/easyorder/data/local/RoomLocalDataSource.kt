package com.sraccelerator.easyorder.data.local

import com.sraccelerator.easyorder.data.local.entities.toEntity
import com.sraccelerator.easyorder.data.local.entities.toModel
import com.sraccelerator.easyorder.data.model.Category
import com.sraccelerator.easyorder.data.model.Product
import javax.inject.Inject

class RoomCategoryLocalDataSource @Inject constructor(
    private val dao: EasyOrderDao
) : CategoryLocalDataSource {

    override suspend fun save(items: List<Category>) {
        dao.insertCategories(items.map { it.toEntity() })
    }

    override suspend fun getAll(): List<Category> {
        return dao.getCategories().map { it.toModel() }
    }

    override suspend fun clearAll() {
        dao.clearCategories()
    }
}

class RoomProductLocalDataSource @Inject constructor(
    private val dao: EasyOrderDao
) : ProductLocalDataSource {

    override suspend fun save(items: List<Product>) {
        // Nota: Para salvar produtos, precisaríamos saber a qual categoria eles pertencem.
        // Em um fluxo real, o repository passaria essa info.
    }

    override suspend fun getAll(): List<Product> {
        return emptyList()
    }

    override suspend fun getByCategoryId(categoryId: Int): List<Product> {
        return dao.getProductsByCategory(categoryId).map { it.toModel() }
    }

    override suspend fun clearAll() {
        dao.clearProducts()
    }
}
