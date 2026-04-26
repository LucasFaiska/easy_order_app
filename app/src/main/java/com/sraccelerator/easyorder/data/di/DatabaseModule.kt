package com.sraccelerator.easyorder.data.di

import android.content.Context
import androidx.room.Room
import com.sraccelerator.easyorder.data.local.EasyOrderDao
import com.sraccelerator.easyorder.data.local.EasyOrderDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): EasyOrderDatabase {
        return Room.databaseBuilder(
            context,
            EasyOrderDatabase::class.java,
            "easy_order_db"
        ).build()
    }

    @Provides
    fun provideDao(database: EasyOrderDatabase): EasyOrderDao {
        return database.dao()
    }
}
