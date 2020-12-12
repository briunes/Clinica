package com.example.clinica

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    val auth = FirebaseAuth.getInstance();


    override fun onCreate(savedInstanceState: Bundle?) {

        val user = auth.currentUser
        if (user != null) {
            val intent = Intent(this, WelcomePage::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(baseContext, "No user is logged.",
                Toast.LENGTH_SHORT).show()
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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