package com.sraccelerator.easyorder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sraccelerator.easyorder.data.local.entities.CategoryEntity
import com.sraccelerator.easyorder.data.local.entities.ProductEntity

@Database(
    entities = [CategoryEntity::class, ProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class EasyOrderDatabase : RoomDatabase() {
    abstract fun dao(): EasyOrderDao
}
