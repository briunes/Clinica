package com.example.clinica

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    val auth = FirebaseAuth.getInstance();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = auth.currentUser

        if (user != null) {

            val intent = Intent(this, CompleteUserData::class.java)
            startActivity(intent)

        }

        val btnRegister = findViewById<Button>(R.id.buttonRegister)
        val btnLogin = findViewById<Button>(R.id.buttonLogin)

        btnRegister.setOnClickListener{
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }






    }
}