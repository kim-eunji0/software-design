package com.cookandroid.closet.roomDB.clothDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ClothDao {

    @Query("SELECT *FROM cloth_table")
    fun getAllData() : List<ClothEntity>

    @Query("SELECT * FROM cloth_table WHERE clothClassify = '상의'")
    fun getTopData() : List<ClothEntity>

    @Query("SELECT *FROM cloth_table WHERE clothClassify = '하의'")
    fun getBottomData() : List<ClothEntity>

    @Query("SELECT *FROM cloth_table WHERE clothClassify = '아우터'")
    fun getOuterData() : List<ClothEntity>

    @Query("SELECT *FROM cloth_table WHERE clothClassify = '모자'")
    fun getCapData() : List<ClothEntity>

    @Query("SELECT *FROM cloth_table WHERE clothClassify = '신발'")
    fun getShoesData() : List<ClothEntity>

    @Query("SELECT *FROM cloth_table WHERE clothClassify = '기타'")
    fun getEtcData() : List<ClothEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(cloth : ClothEntity)

    @Update
    fun update(cloth : ClothEntity)

    @Delete
    fun delete(text : ClothEntity)


}