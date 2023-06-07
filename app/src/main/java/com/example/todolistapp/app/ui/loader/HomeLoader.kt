package com.example.todolistapp.app.ui.loader

import com.example.todolistapp.R
import com.example.todolistapp.app.ui.utils.State
import com.example.todolistapp.storage.entity.CategoryEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeLoader @Inject constructor() {

    private val homeCategoriesMutableStateFlow: MutableStateFlow<List<CategoryEntity>> =
        MutableStateFlow(emptyList())
    val homeCategoriesStateFlow = homeCategoriesMutableStateFlow.asStateFlow()

    fun collectHomeScreen() {
        val listOfCategories = mutableListOf<CategoryEntity>()
        repeat(5) { index ->
            val category = CategoryEntity(0, "Category $index", emptyList(), R.color.my_primary)
            repeat(index) {
                category.addNewTask(
                    "Task number $it",
                    "06/06/2023",
                    "09:4$it",
                    false,
                    mutableMapOf(State.DONE to (it % 2 == 0))
                )
            }
            listOfCategories.add(category)
        }
        homeCategoriesMutableStateFlow.value = listOfCategories
    }
}