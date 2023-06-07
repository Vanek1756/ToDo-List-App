package com.example.todolistapp.app.ui.pages.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.storage.source.DatabaseUserSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val databaseUserSource: DatabaseUserSource
) : ViewModel() {

    private val insertFinishMutableStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val insertFinishStateFlow: StateFlow<Boolean> = insertFinishMutableStateFlow

    fun initCollect() {
        viewModelScope.launch {
            databaseUserSource.insertFinishStateFlow.collect {
                it?.let { insertFinishMutableStateFlow.value = true }
            }
        }
    }

    fun setUserName(userName: String) {
        databaseUserSource.insertUser(userName)
    }
}