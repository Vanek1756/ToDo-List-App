package com.example.todolistapp.storage.repository

import androidx.annotation.WorkerThread
import com.example.todolistapp.storage.dao.UserDao
import com.example.todolistapp.storage.database.DatabaseUserHelper
import com.example.todolistapp.storage.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseUserRepository @Inject constructor(
    private val userDao: UserDao
) : DatabaseUserHelper {

    @WorkerThread
    override suspend fun insertUser(userEntity: UserEntity): Long = userDao.insertUser(userEntity)

    @WorkerThread
    override suspend fun deleteUser(): Int = userDao.deleteUser()

    override suspend fun getUser(): Flow<List<UserEntity>> = userDao.getUser()
}