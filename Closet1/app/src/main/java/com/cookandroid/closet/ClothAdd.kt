package com.cookandroid.closet

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.cookandroid.closet.databinding.ActivityClothAddBinding
import com.cookandroid.closet.roomDB.clothDB.ClothViewModel

class ClothAdd : AppCompatActivity() {

    private lateinit var binding: ActivityClothAddBinding

    lateinit var viewModel: ClothViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_cloth_add)

        viewModel = ViewModelProvider(this).get(ClothViewModel::class.java)

        val clothName = binding.addName
        val clothPicture = binding.addPicture
        val clothMemo = binding.addMemo
        var clothClassify: String = ""

        //라디오 버튼 처리
        binding.radioGroup1.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.addRDTop -> {
                    clothClassify = "상의"
                    binding.radioGroup2.clearCheck()
                    Toast.makeText(this, "상의", Toast.LENGTH_SHORT).show()
                }

                R.id.addRDBottom -> {
                    clothClassify = "하의"
                    binding.radioGroup2.clearCheck()
                    Toast.makeText(this, "하의", Toast.LENGTH_SHORT).show()
                }

                R.id.addRDOuter -> {
                    clothClassify = "아우터"
                    binding.radioGroup2.clearCheck()
                    Toast.makeText(this, "아우터", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.radioGroup2.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.addRDCap -> {
                    clothClassify = "모자"
                    binding.radioGroup1.clearCheck()
                    Toast.makeText(this, "모자", Toast.LENGTH_SHORT).show()
                }

                R.id.addRDShoes -> {
                    clothClassify = "신발"
                    binding.radioGroup1.clearCheck()
                    Toast.makeText(this, "신발", Toast.LENGTH_SHORT).show()
                }

                R.id.addRDEtc -> {
                    clothClassify = "기타"
                    binding.radioGroup1.clearCheck()
                    Toast.makeText(this, "기타", Toast.LENGTH_SHORT).show()
                }
            }
        }
        // 갤러리에서 사진 가져오기
        binding.addPicture.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 50)
        }
        // 추가버튼 클릭 시
        binding.addBtnAdd.setOnClickListener {

            val clothByteArray = clothPicture.drawable
            val clothBitmap = clothByteArray.toBitmap()
            val storeCloth = Bitmap.createScaledBitmap(clothBitmap, 250, 250, true);

            viewModel.insertData(clothName.text.toString(), storeCloth, clothClassify, clothMemo.text.toString())

            Log.d("clothAdd", clothName.text.toString())

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
        // 취소버튼 클릭 시
        binding.addBtnCancel.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }
    //갤러리에서 이미지 가져오기 관련
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == 50) {
            binding.addPicture.setImageURI(data?.data)
        }
    }
}
