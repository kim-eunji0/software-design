package com.cookandroid.closet.clothfragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cookandroid.closet.clothView.ClothView
import com.cookandroid.closet.clothView.ClothViewCap
import com.cookandroid.closet.databinding.FragmentCapBinding
import com.cookandroid.closet.roomDB.ClosetDatabase
import com.cookandroid.closet.roomDB.clothDB.ClothAdapter
import com.cookandroid.closet.roomDB.clothDB.ClothViewModel

class CapFragment : Fragment() {

    private lateinit var viewBinding : FragmentCapBinding

    lateinit var viewModel : ClothViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentCapBinding.inflate(layoutInflater)
        val view = viewBinding.root

        viewModel = ViewModelProvider(this).get(ClothViewModel::class.java)
        viewModel.getCapData()

        val context = container!!.context

        val allRV = viewBinding.capRV

        var intent = Intent(context, ClothViewCap::class.java)

        //어뎁터 연결 + 아이템 클릭 이벤트
        viewModel.clothList.observe(viewLifecycleOwner, Observer {
            val capAdapter = ClothAdapter(it)
            allRV.adapter = capAdapter
            allRV.layoutManager = LinearLayoutManager(context)

            capAdapter.itemClick = object : ClothAdapter.ClothItemClick {
                override fun onClick(view: View, position: Int) {
                    intent.putExtra("positionCap", position)
                    startActivity(intent)

                    Log.d("contentcategory", position.toString())
                }
            }
        })

        return view
    }
}