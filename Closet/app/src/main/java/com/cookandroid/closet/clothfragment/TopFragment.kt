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
import com.cookandroid.closet.clothView.ClothViewTop
import com.cookandroid.closet.databinding.FragmentTopBinding
import com.cookandroid.closet.roomDB.ClosetDatabase
import com.cookandroid.closet.roomDB.clothDB.ClothAdapter
import com.cookandroid.closet.roomDB.clothDB.ClothViewModel

class TopFragment : Fragment() {

    private lateinit var viewBinding : FragmentTopBinding

    lateinit var viewModel : ClothViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentTopBinding.inflate(layoutInflater)
        val view = viewBinding.root

        viewModel = ViewModelProvider(this).get(ClothViewModel::class.java)
        viewModel.getTopData()

        val context = container!!.context

        val allRV = viewBinding.topRV

        var intent = Intent(context, ClothViewTop::class.java)

        //어뎁터 연결 + 아이템 클릭 이벤트
        viewModel.clothList.observe(viewLifecycleOwner, Observer {
            val topAdapter = ClothAdapter(it)
            allRV.adapter = topAdapter
            allRV.layoutManager = LinearLayoutManager(context)

            topAdapter.itemClick = object : ClothAdapter.ClothItemClick {
                override fun onClick(view: View, position: Int) {

                    intent.putExtra("positionTop", position)
                    startActivity(intent)

                    Log.d("contentcategory", position.toString())

                }
            }
        })

        return view
    }
}