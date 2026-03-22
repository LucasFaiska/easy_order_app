package com.sraccelerator.easyorder.data

import com.sraccelerator.easyorder.data.di.IoDispatcher
import com.sraccelerator.easyorder.data.model.Category
import com.sraccelerator.easyorder.data.model.Product
import com.sraccelerator.easyorder.data.remote.RemoteDataSource
import com.sraccelerator.easyorder.data.remote.mapper.toModel
import com.sraccelerator.easyorder.data.remote.network.EasyOrderApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface EasyOrderRepository {
    fun getCategories(restaurantId: Int): Flow<DataState<List<Category>>>
    fun getProductsByCategory(categoryId: Int): Flow<DataState<List<Product>>>
}

internal class EasyOrderRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource, @param:IoDispatcher private val dispatcher: CoroutineDispatcher
) : EasyOrderRepository {

    private val repositoryScope = CoroutineScope(dispatcher + SupervisorJob())

    override fun getCategories(restaurantId: Int): Flow<DataState<List<Category>>> = flow {
        emit(DataState.Loading)
        when (val response = remoteDataSource.getCategories(restaurantId)) {
            is EasyOrderApiResponse.Success -> {
                emit(DataState.Success(response.body.map { it.toModel() }))
            }

            is EasyOrderApiResponse.Error -> {
                emit(DataState.Error(Exception("API Error ${response.code}: ${response.message}")))
            }

            is EasyOrderApiResponse.Exception -> {
                emit(DataState.Error(response.e))
            }
        }
    }.flowOn(dispatcher)

    override fun getProductsByCategory(categoryId: Int): Flow<DataState<List<Product>>> = flow {
        emit(DataState.Loading)
        when (val response = remoteDataSource.getProductsByCategory(categoryId)) {
            is EasyOrderApiResponse.Success -> {
                emit(DataState.Success(response.body.map { it.toModel() }))
            }

            is EasyOrderApiResponse.Error -> {
                emit(DataState.Error(Exception("API Error ${response.code}: ${response.message}")))
            }

            is EasyOrderApiResponse.Exception -> {
                emit(DataState.Error(response.e))
            }
        }
    }.flowOn(dispatcher)
}