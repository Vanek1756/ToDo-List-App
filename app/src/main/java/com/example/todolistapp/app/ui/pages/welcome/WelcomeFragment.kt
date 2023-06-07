package com.example.todolistapp.app.ui.pages.welcome

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.todolistapp.R
import com.example.todolistapp.app.ui.utils.viewBinding
import com.example.todolistapp.databinding.FragmentWelcomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    private val binding by viewBinding(FragmentWelcomeBinding::bind)
    private val viewModel: WelcomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.setupBinding()
        viewModel.subscribe()
        viewModel.initCollect()
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

    private fun WelcomeViewModel.subscribe() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    insertFinishStateFlow.collectLatest {
                        if (it) startHomeFragment()
                    }
                }
            }
        }
    }

    private fun startHomeFragment() {
        val action = WelcomeFragmentDirections.actionWelcomeFragmentToHomeFragment()
        findNavController().navigate(action)
    }
}