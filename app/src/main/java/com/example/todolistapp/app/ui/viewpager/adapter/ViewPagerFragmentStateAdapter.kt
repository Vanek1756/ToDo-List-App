package com.maxbet.pariurisportive.app.ui.viewpager.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerFragmentStateAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {

    private val fragmentList: ArrayList<Fragment> = ArrayList()

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }
}