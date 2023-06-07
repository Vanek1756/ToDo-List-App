package com.example.todolistapp.app.ui.pages.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.todolistapp.NavMainDirections
import com.example.todolistapp.R
import com.example.todolistapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<MainViewModel>()

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        navController =
            (supportFragmentManager.findFragmentById(R.id.fcvMain) as NavHostFragment).navController

        binding.setupBinding()
        viewModel.subscribe()
    }

    private fun ActivityMainBinding.setupBinding(){
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_main),
            drawerLayout
        )
        setupActionBar(navController, appBarConfiguration)
        setupNavigationMenu(navController)
        setupClickListener()
    }

    private fun closeDrawer() {
        binding.drawerLayout.closeDrawer(GravityCompat.START)
    }

    private fun setupActionBar(navController: NavController, appBarConfig: AppBarConfiguration) {
        setupActionBarWithNavController(navController, appBarConfig)
    }

    private fun setupNavigationMenu(navController: NavController) {
        binding.navigationView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    private fun setupClickListener() {
        // Set item click listener to perform action on menu item click.
        binding.navigationView.setNavigationItemSelectedListener { menuItem -> // Toggle the checked state of menuItem.
            menuItem.isChecked = !menuItem.isChecked
            when (menuItem.itemId) {
                R.id.homeFragment -> {
                    navController.navigate(R.id.homeFragment)
                }
            }
            Toast.makeText(
                applicationContext,
                menuItem.title.toString() + " Selected",
                Toast.LENGTH_SHORT
            ).show()
            closeDrawer()
            true
        }
    }



    private fun MainViewModel.subscribe() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    startWelcomeFragmentStateFlow.collectLatest {
                        it?.let { if (it) startWelcomeFragment() }
                    }
                }
            }
        }
    }

    private fun startWelcomeFragment() {
        val action = NavMainDirections.actionGlobalWelcomeFragment()
        navController.navigate(action)
    }
}