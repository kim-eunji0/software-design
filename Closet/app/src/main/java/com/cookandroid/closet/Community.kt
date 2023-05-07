package com.cookandroid.closet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController

class Community : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_community, container, false)

        view.findViewById<Button>(R.id.NavBtn1).setOnClickListener {
            it.findNavController().navigate(R.id.action_community_to_clothCodi)
        }

        view.findViewById<Button>(R.id.NavBtn2).setOnClickListener {
            it.findNavController().navigate(R.id.action_community_to_home2)
        }

        return view
    }

}