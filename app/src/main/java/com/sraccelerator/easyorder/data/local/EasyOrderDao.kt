package com.sraccelerator.easyorder.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sraccelerator.easyorder.data.local.entities.CategoryEntity
import com.sraccelerator.easyorder.data.local.entities.ProductEntity

@Dao
abstract class EasyOrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertCategories(categories: List<CategoryEntity>): List<Long>

    @Query("SELECT * FROM categories")
    abstract suspend fun getCategories(): List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertProducts(products: List<ProductEntity>): List<Long>

    @Query("SELECT * FROM products WHERE categoryId = :categoryId")
    abstract suspend fun getProductsByCategory(categoryId: Int): List<ProductEntity>

    @Query("DELETE FROM categories")
    abstract suspend fun clearCategories(): Int

    @Query("DELETE FROM products")
    abstract suspend fun clearProducts(): Int
}
