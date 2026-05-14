package com.sraccelerator.easyorder.data.local

import com.sraccelerator.easyorder.data.model.Category
import com.sraccelerator.easyorder.data.model.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryCategoryLocalDataSource @Inject constructor() : CategoryLocalDataSource {
    private var cache: List<Category> = emptyList()

    override suspend fun save(items: List<Category>) { cache = items }
    override suspend fun getAll(): List<Category> = cache
    override suspend fun getBy(vararg criteria: QueryCriteria): List<Category> {
        return cache.filter { item ->
            criteria.all { criterion ->
                // Filtro em memória simplificado para o exemplo
                if (criterion is QueryCriteria.Equals && criterion.field == "id") {
                    item.id == criterion.value
                } else true
            }
        }
    }
    override suspend fun clearAll() { cache = emptyList() }
}

@Singleton
class InMemoryProductLocalDataSource @Inject constructor() : ProductLocalDataSource {
    private var cache: List<Product> = emptyList()

    override suspend fun save(items: List<Product>) { cache = items }
    override suspend fun getAll(): List<Product> = cache
    override suspend fun getBy(vararg criteria: QueryCriteria): List<Product> {
        return cache.filter { item ->
            criteria.all { criterion ->
                when (criterion) {
                    is QueryCriteria.Equals -> {
                        // Exemplo: filtrando por categoryId dinamicamente
                        if (criterion.field == "categoryId") {
                            // No seu domínio Product, precisaríamos ter o categoryId exposto
                            // Se não estiver, a implementação de memória apenas retorna o cache
                            true 
                        } else true
                    }
                    else -> true
                }
            }
        }
    }
    override suspend fun clearAll() { cache = emptyList() }
}
