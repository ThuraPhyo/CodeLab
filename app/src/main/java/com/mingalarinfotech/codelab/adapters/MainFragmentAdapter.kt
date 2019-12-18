package com.mingalarinfotech.codelab.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.mingalarinfotech.codelab.fragments.InspirationFragment
import com.mingalarinfotech.codelab.fragments.ProductFragment
import com.mingalarinfotech.codelab.fragments.ShopFragment

class MainFragmentAdapter(private val myContext: Context, fm: FragmentManager, private var totalTabs: Int) :
    FragmentPagerAdapter(fm) {

    //    // this is for fragment tabs
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ProductFragment()
            1 -> InspirationFragment()
            else -> ShopFragment()
        }
    }


    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}