package com.cookandroid.closet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LogIn : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    // 작업 초기화 시 사용자가 자동 로그인 되어 있는지 확인
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "로그인 완료", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        auth = Firebase.auth

        val loginBtn = findViewById<Button>(R.id.BtnLogIn)

        loginBtn.setOnClickListener {
            val ID = findViewById<EditText>(R.id.logInID)
            val PW = findViewById<EditText>(R.id.logInPW)

            auth.signInWithEmailAndPassword(ID.text.toString(), PW.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(this, "로그인 완료", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this, "ID/PW를 확인해주세요", Toast.LENGTH_SHORT).show()
                    }
                }

        }

        val registerBtn = findViewById<Button>(R.id.logInBtnRegister)

        registerBtn.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }


}