package com.cookandroid.closet.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cookandroid.closet.roomDB.clothDB.ClothDao
import com.cookandroid.closet.roomDB.clothDB.ClothEntity
import com.cookandroid.closet.roomDB.clothDB.PictureTypeConvert

@Database(entities = [ClothEntity::class], version = 3)
@TypeConverters(PictureTypeConvert::class)

abstract class ClosetDatabase : RoomDatabase() {

    abstract fun clothDao() : ClothDao

    companion object {
        @Volatile
        private var INSTANCE : ClosetDatabase? = null

        fun getDatabase(
            context : Context
        ) : ClosetDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ClosetDatabase::class.java,
                    "cloth_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}