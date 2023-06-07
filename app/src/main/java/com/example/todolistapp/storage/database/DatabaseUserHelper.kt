package com.example.todolistapp.storage.database

import com.example.todolistapp.storage.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface DatabaseUserHelper {

    suspend fun insertUser(userEntity: UserEntity): Long

    suspend fun deleteUser(): Int

    suspend fun getUser(): Flow<List<UserEntity>>

}