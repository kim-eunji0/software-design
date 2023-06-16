package com.cookandroid.closet.roomDB.clothDB

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cookandroid.closet.roomDB.ClosetDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClothViewModel(application: Application) : AndroidViewModel(application) {

    val context = getApplication<Application>().applicationContext
    val db = ClosetDatabase.getDatabase(context)

    private var _clothList = MutableLiveData<List<ClothEntity>>()
    val clothList : LiveData<List<ClothEntity>>
        get() = _clothList

    val repository = ClothRepository(context)

    fun getAllData() = viewModelScope.launch(Dispatchers.IO) {
        Log.d("ClosetViewModel", db.clothDao().getAllData().toString())
        _clothList.postValue(repository.getClothList())
    }

    fun getTopData() = viewModelScope.launch(Dispatchers.IO) {
        Log.d("TopViewModel", db.clothDao().getTopData().toString())
        _clothList.postValue(repository.getTopList())
    }

    fun getBottomData() = viewModelScope.launch(Dispatchers.IO) {
        Log.d("BottomViewModel", db.clothDao().getBottomData().toString())
        _clothList.postValue(repository.getBottomList())
    }

    fun getOuterData() = viewModelScope.launch(Dispatchers.IO) {
        Log.d("OuterViewModel", db.clothDao().getOuterData().toString())
        _clothList.postValue(repository.getOuterList())
    }

    fun getCapData() = viewModelScope.launch(Dispatchers.IO) {
        Log.d("BottomViewModel", db.clothDao().getCapData().toString())
        _clothList.postValue(repository.getCapList())
    }

    fun getShoesData() = viewModelScope.launch(Dispatchers.IO) {
        Log.d("BottomViewModel", db.clothDao().getShoesData().toString())
        _clothList.postValue(repository.getShoesList())
    }

    fun getEtcData() = viewModelScope.launch(Dispatchers.IO) {
        Log.d("BottomViewModel", db.clothDao().getEtcData().toString())
        _clothList.postValue(repository.getEtcList())
    }

    fun insertData(clothName : String, clothPicture : Bitmap,
                   clothClassify : String, clothMemo : String)
    = viewModelScope.launch(Dispatchers.IO) {
        repository.insertClothData(clothName, clothPicture, clothClassify, clothMemo)
    }
}