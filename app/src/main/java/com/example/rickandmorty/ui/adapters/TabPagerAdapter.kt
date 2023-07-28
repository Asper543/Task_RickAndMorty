package com.example.rickandmorty.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabPagerAdapter(fragment:Fragment) : FragmentStateAdapter(fragment) {

    private val fragmentList:ArrayList<Pair<String,Fragment>> = arrayListOf()
    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment {
         return  fragmentList[position].second
    }
    fun getTitle(position: Int) = fragmentList[position].first

    fun setFragments(title:String,fragment:Fragment) =
        this.apply { fragmentList.add(Pair(title,fragment))}
}

