package com.example.clinica

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Contacts : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)


        val btnCall = findViewById<Button>(R.id.btnCall)


        btnCall.setOnClickListener {

        }

    }
}