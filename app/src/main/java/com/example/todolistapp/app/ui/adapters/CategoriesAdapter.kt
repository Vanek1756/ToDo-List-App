package com.example.todolistapp.app.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.app.ui.utils.State
import com.example.todolistapp.databinding.ItemHomeCategoryBinding
import com.example.todolistapp.storage.entity.CategoryEntity

class CategoryAdapter(
    private val onCategoryClick: (CategoryEntity) -> Unit,
    private val onEditCategoryClick: (CategoryEntity) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    var categories: List<CategoryEntity> = emptyList()
        set(newValue) {
            val diffCallback = CategoryDiffCallback(field, newValue)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newValue
            diffResult.dispatchUpdatesTo(this)
        }

    override fun getItemCount(): Int = categories.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeCategoryBinding.inflate(inflater, parent, false)
        return CategoryViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        val context = holder.itemView.context
        with(holder.binding) {
            root.setOnClickListener { onCategoryClick(category) }
            btnEditCategory.setOnClickListener { onEditCategoryClick(category) }

            tvCategoryName.text = category.categoryName
            tvTaskCount.text =
                "${category.listOfTasks.count()} " + context.getString(R.string.home_tasks)
            val indicatorColor = context.getColor(category.categoryColorId)
            progressBar.setIndicatorColor(indicatorColor)
            progressBar.trackColor = setTransparencyToColor(indicatorColor, 128)
            val progress = category.listOfTasks.count { it.property[State.DONE] == true }
            progressBar.max = category.listOfTasks.count()
            progressBar.progress = progress
        }
    }

    class CategoryViewHolder(
        val binding: ItemHomeCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root)

}