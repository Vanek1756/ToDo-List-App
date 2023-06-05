package com.example.todolistapp.app.ui.pages.welcome

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.todolistapp.R
import com.example.todolistapp.app.ui.utils.viewBinding
import com.example.todolistapp.databinding.FragmentWelcomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    private val binding by viewBinding(FragmentWelcomeBinding::bind)
    private val viewModel: WelcomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.setupBinding()
    }

    private fun FragmentWelcomeBinding.setupBinding() {
        etName.doAfterTextChanged {
            if (it.isNullOrBlank()) {
                btnStart.isEnabled = false
                etName.text?.clear()
            } else {
                btnStart.isEnabled = true
            }
        }
        btnStart.setOnClickListener { viewModel.setUserName(etName.text.toString()) }
    }
}