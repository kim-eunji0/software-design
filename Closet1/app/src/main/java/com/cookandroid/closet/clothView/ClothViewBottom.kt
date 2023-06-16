package com.cookandroid.closet.clothView

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cookandroid.closet.MainActivity
import com.cookandroid.closet.R
import com.cookandroid.closet.clothModi.ClothModify
import com.cookandroid.closet.clothModi.ClothModifyBottom
import com.cookandroid.closet.databinding.ActivityClothViewBinding
import com.cookandroid.closet.roomDB.ClosetDatabase
import com.cookandroid.closet.roomDB.clothDB.ClothAdapter
import com.cookandroid.closet.roomDB.clothDB.ClothEntity
import com.cookandroid.closet.roomDB.clothDB.ClothViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClothViewBottom : AppCompatActivity() {
    private lateinit var binding : ActivityClothViewBinding

    lateinit var viewModel : ClothViewModel

    var db = ClosetDatabase.getDatabase(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_cloth_view)

        viewModel = ViewModelProvider(this).get(ClothViewModel::class.java)
        viewModel.getBottomData()

        val position = intent.getIntExtra("positionBottom", 0)

        viewModel.clothList.observe(this, Observer {
            val clothAdapter = ClothAdapter(it)

            val clothName = clothAdapter.getClothName(position)
            binding.viewName.setText(clothName)

            val clothPicture = clothAdapter.getClothPicture(position)
            binding.viewPicture.setImageBitmap(clothPicture)

            val clothCategory = clothAdapter.getClothCategory(position)
            binding.viewCategory.setText(clothCategory)

            val clothMemo = clothAdapter.getClothMemo(position)
            binding.viewMemo.setText(clothMemo)
        })

        binding.clothModify.setOnClickListener {
            val intent = Intent(this, ClothModifyBottom::class.java)
            intent.putExtra("modifyitemBottom", position)
            startActivity(intent)
        }

        binding.clothDelete.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val result = db.clothDao().getBottomData()
                val clothEntity = result[position]
                db.clothDao().delete(clothEntity)

                val deleteResult = db.clothDao().getAllData()
                var resultPosition : Int
                val size = deleteResult.size
                var deleteEntity : ClothEntity

                for(i in 0..size) {
                    if(clothEntity.id == deleteResult[i].id) {
                        resultPosition = i
                        Log.d("object", resultPosition.toString())
                        deleteEntity = deleteResult[resultPosition]
                        db.clothDao().delete(deleteEntity)
                    }
                }
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "삭제 완료", Toast.LENGTH_SHORT).show()
        }

        binding.home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}