package com.example.todolistapp.storage.source

import com.example.todolistapp.storage.entity.UserEntity
import com.example.todolistapp.storage.repository.DatabaseUserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseUserSource @Inject constructor(private val databaseUserRepository: DatabaseUserRepository) {

    private val insertFinishMutableStateFlow: MutableStateFlow<UserEntity?> = MutableStateFlow(null)
    val insertFinishStateFlow: StateFlow<UserEntity?> = insertFinishMutableStateFlow
    private val deleteFinishMutableStateFlow: MutableStateFlow<UserEntity?> = MutableStateFlow(null)
    val deleteFinishStateFlow = deleteFinishMutableStateFlow.asStateFlow()

    private val scopeIo = CoroutineScope(Dispatchers.IO)

    suspend fun getUserStateFlow() = databaseUserRepository.getUser().stateIn(scopeIo)

    fun insertUser(userName: String) {
        scopeIo.launch {
            val userEntity = UserEntity(0, userName)
            databaseUserRepository.insertUser(userEntity).apply {
                insertFinishMutableStateFlow.value = userEntity
            }
        }
    }

    fun deleteUser() {
        scopeIo.launch {
            databaseUserRepository.deleteUser()
        }
    }
}