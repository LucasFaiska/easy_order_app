package com.sraccelerator.easyorder.data

import com.sraccelerator.easyorder.data.di.IoDispatcher
import com.sraccelerator.easyorder.data.local.CategoryLocalDataSource
import com.sraccelerator.easyorder.data.local.ProductLocalDataSource
import com.sraccelerator.easyorder.data.model.Category
import com.sraccelerator.easyorder.data.model.Product
import com.sraccelerator.easyorder.data.remote.RemoteDataSource
import com.sraccelerator.easyorder.data.remote.mapper.toModel
import com.sraccelerator.easyorder.data.remote.network.EasyOrderApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface EasyOrderRepository {
    fun getCategories(restaurantId: Int): Flow<DataState<List<Category>>>
    fun getProductsByCategory(categoryId: Int): Flow<DataState<List<Product>>>
}

internal class EasyOrderRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val categoryLocal: CategoryLocalDataSource,
    private val productLocal: ProductLocalDataSource,
    @param:IoDispatcher private val dispatcher: CoroutineDispatcher
) : EasyOrderRepository {

    override fun getCategories(restaurantId: Int): Flow<DataState<List<Category>>> = flow {
        emit(DataState.Loading)
        when (val response = remoteDataSource.getCategories(restaurantId)) {
            is EasyOrderApiResponse.Success -> {
                val categories = response.body.map { it.toModel() }
                categoryLocal.save(categories)
                emit(DataState.Success(categories))
            }

            else -> {
                val localCategories = categoryLocal.getAll()
                if (localCategories.isNotEmpty()) {
                    emit(DataState.Success(localCategories))
                } else {
                    val error = if (response is EasyOrderApiResponse.Exception) response.e else Exception("API Error")
                    emit(DataState.Error(error))
                }
            }
        }
    }.flowOn(dispatcher)

    override fun getProductsByCategory(categoryId: Int): Flow<DataState<List<Product>>> = flow {
        emit(DataState.Loading)
        when (val response = remoteDataSource.getProductsByCategory(categoryId)) {
            is EasyOrderApiResponse.Success -> {
                val products = response.body.map { it.toModel() }
                productLocal.save(products)
                emit(DataState.Success(products))
            }

            else -> {
                val localProducts = productLocal.getByCategoryId(categoryId)
                if (localProducts.isNotEmpty()) {
                    emit(DataState.Success(localProducts))
                } else {
                    val error = if (response is EasyOrderApiResponse.Exception) response.e else Exception("API Error")
                    emit(DataState.Error(error))
                }
            }
        }
    }.flowOn(dispatcher)
}
