package com.cookandroid.closet

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth

        val registerBtn = findViewById<Button>(R.id.BtnRegister)

        registerBtn.setOnClickListener {
            val ID = findViewById<EditText>(R.id.registerID)
            val PW = findViewById<EditText>(R.id.registerPW)

            auth.createUserWithEmailAndPassword(ID.text.toString(), PW.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this, LogIn::class.java)
                        startActivity(intent)

                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this, LogIn::class.java)
                        startActivity(intent)

                    }
                }
        }

    }
}