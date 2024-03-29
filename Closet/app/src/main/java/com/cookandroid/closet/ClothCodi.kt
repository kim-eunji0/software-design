package com.cookandroid.closet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController

class ClothCodi : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_cloth_codi, container, false)

        view.findViewById<Button>(R.id.NavBtn2).setOnClickListener {
            it.findNavController().navigate(R.id.action_clothCodi_to_home2)
        }

        view.findViewById<Button>(R.id.NavBtn3).setOnClickListener {
            it.findNavController().navigate(R.id.action_clothCodi_to_community)
        }

        return view
    }

}