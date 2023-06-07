package com.example.todolistapp.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todolistapp.storage.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity): Long

    @Query("DELETE FROM user_table")
    suspend fun deleteUser(): Int

    @Query("SELECT * FROM user_table")
    fun getUser(): Flow<List<UserEntity>>

}