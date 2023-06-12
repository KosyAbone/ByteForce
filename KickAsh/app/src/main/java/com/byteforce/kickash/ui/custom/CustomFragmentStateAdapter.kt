package com.byteforce.kickash.ui.custom

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class CustomFragmentStateAdapter(
    supportFragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    var frag: List<Fragment>
) :
    FragmentStateAdapter(supportFragmentManager, lifecycle) {


    override fun getItemCount(): Int {
        return frag.size
    }

    override fun createFragment(position: Int): Fragment {
        return frag[position]
    }
}