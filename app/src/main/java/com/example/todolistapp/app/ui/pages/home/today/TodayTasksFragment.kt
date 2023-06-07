package com.example.todolistapp.app.ui.pages.home.today

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.todolistapp.R
import com.example.todolistapp.app.ui.utils.viewBinding
import com.example.todolistapp.databinding.FragmentTodayTasksBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodayTasksFragment : Fragment(R.layout.fragment_today_tasks) {

    companion object {
        const val TAG = "TodayTasksFragment"

        @JvmStatic
        fun newInstance() = TodayTasksFragment()
            .apply {
                arguments = Bundle().apply {}
            }
    }

    private val binding by viewBinding(FragmentTodayTasksBinding::bind)

}