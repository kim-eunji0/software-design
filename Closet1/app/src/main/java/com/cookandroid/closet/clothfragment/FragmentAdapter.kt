package com.cookandroid.closet.clothfragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FragmentAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 7
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {
                AllFragment()
            }
            1 -> {
                TopFragment()
            }
            2 -> {
                BottomFragment()
            }
            3-> {
                OuterFragment()
            }
            4 -> {
                CapFragment()
            }
            5 -> {
                ShoesFragment()
            }
            else -> {
                EtcFragment()
            }

        }
    }

}