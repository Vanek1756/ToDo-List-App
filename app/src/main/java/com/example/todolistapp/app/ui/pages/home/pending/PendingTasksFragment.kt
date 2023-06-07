package com.example.todolistapp.app.ui.pages.home.pending

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.todolistapp.R
import com.example.todolistapp.app.ui.utils.viewBinding
import com.example.todolistapp.databinding.FragmentPendingTasksBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PendingTasksFragment : Fragment(R.layout.fragment_pending_tasks) {

    companion object {
        const val TAG = "PendingTasksFragment"

        @JvmStatic
        fun newInstance() = PendingTasksFragment()
            .apply {
                arguments = Bundle().apply {}
            }
    }

    private val binding by viewBinding(FragmentPendingTasksBinding::bind)

}