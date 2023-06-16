package com.cookandroid.closet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.cookandroid.closet.clothfragment.FragmentAdapter
import com.cookandroid.closet.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        fun createTab(tabName: String): View {

            val tab = LayoutInflater.from(this).inflate(R.layout.tab_item, null)

            val text = tab.findViewById<TextView>(R.id.text_name) as TextView
            text.text = tabName

            return tab
        }

        val fragAdapter = FragmentAdapter(supportFragmentManager)
        binding.listviewPager.adapter = fragAdapter

        val tab = binding.tabLayout
        tab.addTab(tab.newTab().setCustomView(createTab("전체")))
        tab.addTab(tab.newTab().setCustomView(createTab("상의")))
        tab.addTab(tab.newTab().setCustomView(createTab("하의")))
        tab.addTab(tab.newTab().setCustomView(createTab("아우터")))
        tab.addTab(tab.newTab().setCustomView(createTab("모자")))
        tab.addTab(tab.newTab().setCustomView(createTab("신발")))
        tab.addTab(tab.newTab().setCustomView(createTab("기타")))

        binding.listviewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab))

        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.listviewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        binding.homeBtnAddCloth.setOnClickListener {
            val intent = Intent(this, ClothAdd::class.java)
            startActivity(intent)
        }

    }
}