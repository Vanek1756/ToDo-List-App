package com.example.todolistapp.app.ui.pages.home.done

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.todolistapp.R
import com.example.todolistapp.app.ui.utils.viewBinding
import com.example.todolistapp.databinding.FragmentDoneTasksBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoneTasksFragment : Fragment(R.layout.fragment_done_tasks) {

    companion object {
        const val TAG = "DoneTasksFragment"

        @JvmStatic
        fun newInstance() = DoneTasksFragment()
            .apply {
                arguments = Bundle().apply {}
            }
    }

    private val binding by viewBinding(FragmentDoneTasksBinding::bind)

}