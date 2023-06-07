package com.example.todolistapp.app.ui.pages.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.storage.source.DatabaseUserSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val databaseUserSource: DatabaseUserSource
) : ViewModel() {

    private val startWelcomeFragmentMutableStateFlow: MutableStateFlow<Boolean?> =
        MutableStateFlow(null)
    val startWelcomeFragmentStateFlow = startWelcomeFragmentMutableStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            databaseUserSource.getUserStateFlow().collectLatest {
                startWelcomeFragmentMutableStateFlow.value = it.isEmpty()
            }
        }
    }

}