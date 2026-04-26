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

    override suspend fun getBy(vararg criteria: QueryCriteria): List<Category> {
        val query = RoomQueryBuilder.build("categories", criteria)
        return dao.getCategoriesRaw(query).map { it.toModel() }
    }

    override suspend fun clearAll() {
        dao.clearCategories()
    }
}

class RoomProductLocalDataSource @Inject constructor(
    private val dao: EasyOrderDao
) : ProductLocalDataSource {

    override suspend fun save(items: List<Product>) {
        // Nota: Em uma implementação completa, o mapper lidaria com a conversão
        // incluindo os IDs necessários para o vínculo no banco.
    }

    override suspend fun getAll(): List<Product> {
        return emptyList()
    }

    override suspend fun getBy(vararg criteria: QueryCriteria): List<Product> {
        val query = RoomQueryBuilder.build("products", criteria)
        return dao.getProductsRaw(query).map { it.toModel() }
    }

    override suspend fun clearAll() {
        dao.clearProducts()
    }
}
