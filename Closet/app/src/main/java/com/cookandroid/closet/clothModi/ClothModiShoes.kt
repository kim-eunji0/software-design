package com.cookandroid.closet.clothModi

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cookandroid.closet.R
import com.cookandroid.closet.clothView.ClothViewCap
import com.cookandroid.closet.clothView.ClothViewShoes
import com.cookandroid.closet.clothView.ClothViewTop
import com.cookandroid.closet.databinding.ActivityClothModifyBinding
import com.cookandroid.closet.roomDB.ClosetDatabase
import com.cookandroid.closet.roomDB.clothDB.ClothAdapter
import com.cookandroid.closet.roomDB.clothDB.ClothViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClothModiShoes : AppCompatActivity() {
    private lateinit var binding : ActivityClothModifyBinding

    lateinit var viewModel : ClothViewModel

    var db = ClosetDatabase.getDatabase(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_cloth_modify)

        viewModel = ViewModelProvider(this).get(ClothViewModel::class.java)
        viewModel.getShoesData()

        val position = intent.getIntExtra("modifyitemShoes", 0)

        viewModel.clothList.observe(this, Observer {
            val clothAdapter = ClothAdapter(it)

            // 수정 화면에 아이템 받아오기
            val clothName = clothAdapter.getClothName(position)
            binding.reviseName.setText(clothName)

            var clothPicture = clothAdapter.getClothPicture(position)
            binding.revisePicture.setImageBitmap(clothPicture)

            val clothMemo = clothAdapter.getClothMemo(position)
            binding.reviseMemo.setText(clothMemo)

            val clothCategory = clothAdapter.getClothCategory(position)

            when(clothCategory) {
                "상의" -> {
                    binding.reviseRDTop.isChecked = true
                }
                "하의" -> {
                    binding.reviseRDBottom.isChecked = true
                }
                "아우터" -> {
                    binding.reviseRDOuter.isChecked = true
                }
                "모자" -> {
                    binding.reviseRDCap.isChecked = true
                }
                "신발" -> {
                    binding.reviseRDShoes.isChecked = true
                }
                "기타" -> {
                    binding.reviseRDEtc.isChecked = true
                }
            }
        })

        // 카테고리 수정 정보 받아오기
        // 라디오 버튼 처리
        var reviseclothCategory : String = ""
        binding.radioGroup1.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.reviseRDTop -> {
                    reviseclothCategory = "상의"
                    binding.radioGroup2.clearCheck()
                    Toast.makeText(this, "상의", Toast.LENGTH_SHORT).show()
                }

                R.id.reviseRDBottom -> {
                    reviseclothCategory = "하의"
                    binding.radioGroup2.clearCheck()
                    Toast.makeText(this, "하의", Toast.LENGTH_SHORT).show()
                }

                R.id.reviseRDOuter -> {
                    reviseclothCategory = "아우터"
                    binding.radioGroup2.clearCheck()
                    Toast.makeText(this, "아우터", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.radioGroup2.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.reviseRDCap -> {
                    reviseclothCategory = "모자"
                    binding.radioGroup1.clearCheck()
                    Toast.makeText(this, "모자", Toast.LENGTH_SHORT).show()
                }

                R.id.reviseRDShoes -> {
                    reviseclothCategory = "신발"
                    binding.radioGroup1.clearCheck()
                    Toast.makeText(this, "신발", Toast.LENGTH_SHORT).show()
                }

                R.id.reviseRDEtc -> {
                    reviseclothCategory = "기타"
                    binding.radioGroup1.clearCheck()
                    Toast.makeText(this, "기타", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.reviseBtnComplete.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val result = db.clothDao().getShoesData()
                val clothEntity = result[position]
                clothEntity.clothName = binding.reviseName.text.toString()
                clothEntity.clothClassify = reviseclothCategory
                clothEntity.clothMemo = binding.reviseMemo.text.toString()

                val clothByteArray = binding.revisePicture.drawable
                val clothBitmap = clothByteArray.toBitmap()
                val reviseCloth = Bitmap.createScaledBitmap(clothBitmap, 250, 250, true);

                clothEntity.clothPicture = reviseCloth

                db.clothDao().update(clothEntity)
            }
            val intent = Intent(this, ClothViewShoes::class.java)
            intent.putExtra("positionShoes", position)
            startActivity(intent)
        }

        binding.reviseBtnCancel.setOnClickListener {
            val intent = Intent(this, ClothViewShoes::class.java)
            intent.putExtra("positionShoes", position)
            startActivity(intent)
        }
    }
    //갤러리에서 이미지 가져오기 관련
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == 50) {
            binding.revisePicture.setImageURI(data?.data)
        }
    }
}