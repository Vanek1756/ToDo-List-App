package com.example.todolistapp.app.ui.pages.welcome

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor() : ViewModel() {

    private var userName = ""

    fun setUserName(txt: String){
        userName
    }
}