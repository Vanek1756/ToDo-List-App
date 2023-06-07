package com.example.todolistapp.app.application.di

import android.content.Context
import com.example.todolistapp.storage.dao.UserDao
import com.example.todolistapp.storage.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.getDatabase(context)

    @Provides
    fun provideBetDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

}