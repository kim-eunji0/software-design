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
import com.cookandroid.closet.clothView.ClothViewEtc
import com.cookandroid.closet.databinding.FragmentEtcBinding
import com.cookandroid.closet.roomDB.ClosetDatabase
import com.cookandroid.closet.roomDB.clothDB.ClothAdapter
import com.cookandroid.closet.roomDB.clothDB.ClothViewModel

class EtcFragment : Fragment() {

    private lateinit var viewBinding : FragmentEtcBinding

    lateinit var viewModel : ClothViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentEtcBinding.inflate(layoutInflater)
        val view = viewBinding.root

        viewModel = ViewModelProvider(this).get(ClothViewModel::class.java)
        viewModel.getEtcData()

        val context = container!!.context

        val allRV = viewBinding.etcRV

        var intent = Intent(context, ClothViewEtc::class.java)

        //어뎁터 연결 + 아이템 클릭 이벤트
        viewModel.clothList.observe(viewLifecycleOwner, Observer {
            val etcAdapter = ClothAdapter(it)
            allRV.adapter = etcAdapter
            allRV.layoutManager = LinearLayoutManager(context)

            etcAdapter.itemClick = object : ClothAdapter.ClothItemClick {
                override fun onClick(view: View, position: Int) {
                    intent.putExtra("positionEtc", position)
                    startActivity(intent)

                    Log.d("contentcategory", position.toString())
                }
            }
        })

        return view
    }
}