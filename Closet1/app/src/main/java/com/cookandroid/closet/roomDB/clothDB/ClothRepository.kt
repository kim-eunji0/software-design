package com.cookandroid.closet.roomDB.clothDB

import android.content.Context
import android.graphics.Bitmap
import com.cookandroid.closet.roomDB.ClosetDatabase

class ClothRepository(context : Context) {

    val db = ClosetDatabase.getDatabase(context)

    fun getClothList() = db.clothDao().getAllData()

    fun getTopList() = db.clothDao().getTopData()

    fun getBottomList() = db.clothDao().getBottomData()

    fun getOuterList() = db.clothDao().getOuterData()

    fun getCapList() = db.clothDao().getCapData()

    fun getShoesList() = db.clothDao().getShoesData()

    fun getEtcList() = db.clothDao().getEtcData()

    fun insertClothData(clothName : String, clothPicture : Bitmap,
                       clothClassify : String, clothMemo : String)
    = db.clothDao().insert(ClothEntity(0, clothName, clothPicture, clothClassify, clothMemo))

//    fun updateClothData(clothName : String, clothPicture : Bitmap,
//                         clothClassify : String, clothMemo : String)
//    = db.clothDao().update(ClothEntity(0, clothName, clothPicture, clothClassify, clothMemo))

}