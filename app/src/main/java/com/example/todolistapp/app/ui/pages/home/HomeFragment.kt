package com.example.todolistapp.app.ui.pages.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistapp.R
import com.example.todolistapp.app.ui.adapters.HomeCategoryAdapter
import com.example.todolistapp.app.ui.pages.home.done.DoneTasksFragment
import com.example.todolistapp.app.ui.pages.home.pending.PendingTasksFragment
import com.example.todolistapp.app.ui.pages.home.today.TodayTasksFragment
import com.example.todolistapp.app.ui.utils.viewBinding
import com.example.todolistapp.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.maxbet.pariurisportive.app.ui.viewpager.adapter.ViewPagerFragmentStateAdapter
import com.maxbet.pariurisportive.app.ui.viewpager.transformer.ZoomOutPageTransformer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()

    private val viewPagerAdapter by lazy {
        ViewPagerFragmentStateAdapter(
            requireActivity().supportFragmentManager,
            lifecycle
        ).apply {
            addFragment(TodayTasksFragment.newInstance())
            addFragment(PendingTasksFragment.newInstance())
            addFragment(DoneTasksFragment.newInstance())
        }
    }

    private val categoryAdapter by lazy {
        HomeCategoryAdapter(
            onCategoryClick = {

            },
            onEditCategoryClick = {

            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.setupBinding()
        viewModel.subscribe()
    }

    private fun FragmentHomeBinding.setupBinding() {
        tasksViewpager.adapter = viewPagerAdapter
        tasksViewpager.setPageTransformer(ZoomOutPageTransformer())
        val tabTitles = requireContext().resources.getStringArray(R.array.home_tab_bar)
        TabLayoutMediator(tasksTabLayout, tasksViewpager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvCategories.layoutManager = layoutManager
        rvCategories.adapter = categoryAdapter
        rvCategories.itemAnimator = null

        fabAddNewTasks.setOnClickListener { startCreateNewTask() }
    }

    @SuppressLint("SetTextI18n")
    private fun HomeViewModel.subscribe() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    userNameStateFlow.collectLatest {
                        binding.tvMessageHello.text = getString(R.string.home_hello) + " $it!"
                    }
                }
                launch {
                    homeCategoriesStateFlow.collectLatest {
                        categoryAdapter.categories = it
                    }
                }
            }
        }
    }


    private fun startCreateNewTask() {
//        TODO("Not yet implemented")
    }
}