package com.example.todolistapp.app.ui.adapters

import android.graphics.Color
import androidx.recyclerview.widget.DiffUtil
import com.example.todolistapp.storage.entity.CategoryEntity

class CategoryDiffCallback(
    private val oldList: List<CategoryEntity>,
    private val newList: List<CategoryEntity>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = oldList[oldItemPosition]
        val newUser = newList[newItemPosition]
        return oldUser.categoryId == newUser.categoryId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = oldList[oldItemPosition]
        val newUser = newList[newItemPosition]
        return oldUser == newUser
    }

}

fun setTransparencyToColor(color: Int, transparency: Int): Int {
    // Отримати компоненти RGB з вихідного коліру
    val red = Color.red(color)
    val green = Color.green(color)
    val blue = Color.blue(color)

    // Створити новий колір зі зміненим альфа-каналом
    return Color.argb(transparency, red, green, blue)
}