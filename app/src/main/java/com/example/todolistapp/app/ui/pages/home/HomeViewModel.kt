package com.example.todolistapp.app.ui.pages.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.app.ui.loader.HomeLoader
import com.example.todolistapp.storage.source.DatabaseUserSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val databaseUserSource: DatabaseUserSource,
    private val homeLoader: HomeLoader,
) : ViewModel() {

    private val userNameMutableStateFlow: MutableStateFlow<String> = MutableStateFlow("")
    val userNameStateFlow = userNameMutableStateFlow.asStateFlow()

    val homeCategoriesStateFlow = homeLoader.homeCategoriesStateFlow

    init {
        viewModelScope.launch {
            databaseUserSource.getUserStateFlow().collectLatest {
                userNameMutableStateFlow.value = it.firstOrNull()?.userName ?: ""
            }
        }
        homeLoader.collectHomeScreen()
    }
}