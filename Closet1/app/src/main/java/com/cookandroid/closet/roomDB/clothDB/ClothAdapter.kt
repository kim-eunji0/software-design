package com.cookandroid.closet.roomDB.clothDB

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.closet.R

class ClothAdapter(private val dataSet : List<ClothEntity>) : RecyclerView.Adapter<ClothAdapter.ViewHolder>() {

    interface ClothItemClick {
        fun onClick(view : View, position : Int)
    }

    fun getClothName(position : Int) : String{
        var clothName = dataSet[position].clothName
        return clothName
    }
    fun getClothPicture(position : Int) : Bitmap{
        var clothPicture = dataSet[position].clothPicture
        return clothPicture
    }
    fun getClothCategory(position : Int) : String{
        var clothCategory = dataSet[position].clothClassify
        return clothCategory
    }
    fun getClothMemo(position : Int) : String{
        var clothMemo = dataSet[position].clothMemo
        return clothMemo
    }

    var itemClick : ClothItemClick? = null

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val clothName : TextView = view.findViewById<TextView>(R.id.textViewItem)
        val clothPicture : ImageView = view.findViewById<ImageView>(R.id.pictureViewItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(itemClick != null) {
            holder?.itemView?.setOnClickListener { v ->
                itemClick?.onClick(v,position)
            }
        }

        holder.clothName.text = dataSet[position].clothName
        holder.clothPicture.setImageBitmap(dataSet[position].clothPicture)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}