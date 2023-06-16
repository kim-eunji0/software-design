package com.cookandroid.closet.roomDB.clothDB

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

@Entity(tableName = "cloth_table")
data class ClothEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int,
    @ColumnInfo(name = "name")
    var clothName : String,
    @ColumnInfo(name = "picture")
    var clothPicture : Bitmap,
    @ColumnInfo
    var clothClassify : String,
    @ColumnInfo
    var clothMemo : String
    )

class PictureTypeConvert {

    // Bitmap -> ByteArray 변환
    @TypeConverter
    fun getToByteArray(bitmap : Bitmap) : ByteArray{
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }

    // ByteArray -> Bitmap 변환
    @TypeConverter
    fun getToBitmap(bytes : ByteArray) : Bitmap{
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }
}