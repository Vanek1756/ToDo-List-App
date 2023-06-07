package com.example.todolistapp.app.ui.pages.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.todolistapp.R
import com.example.todolistapp.app.ui.utils.viewBinding
import com.example.todolistapp.databinding.FragmentCategoriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    private val binding by viewBinding(FragmentCategoriesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.setupBinding()
//        viewModel.subscribe()
    }

    private fun FragmentCategoriesBinding.setupBinding() {
        rvCategories.layoutManager =  GridLayoutManager(context, 2)
//        rvCategories.adapter = categoryAdapter
        rvCategories.itemAnimator = null

    }

//    private fun HomeViewModel.subscribe() {
//        lifecycleScope.launch {
//            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                launch {
//                    userNameStateFlow.collectLatest {
//                    }
//                }
//                launch {
//                    homeCategoriesStateFlow.collectLatest {
//                    }
//                }
//            }
//        }
//    }

}