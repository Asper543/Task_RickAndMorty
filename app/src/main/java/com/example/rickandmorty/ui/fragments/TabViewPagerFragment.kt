package com.example.rickandmorty.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.rickandmorty.R
import com.example.rickandmorty.ui.adapters.TabPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

const val CHARACTERS = "CHARACTERS"
const val LOCATIONS = "LOCATIONS"
class TabViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tab_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TabPagerAdapter(this)
            .setFragments(CHARACTERS,CharactersFragment())
            .setFragments(LOCATIONS,LocationsFragment())
        val tabLayout: TabLayout = view.findViewById(R.id.tabLayout)
        val viewPager: ViewPager2 = view.findViewById(R.id.viewPager)
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout,viewPager){ tab,position->
            tab.text = adapter.getTitle(position)
        }.attach()
    }
}