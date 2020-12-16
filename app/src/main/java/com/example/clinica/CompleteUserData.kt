package com.example.clinica

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

data class User2(
        var name            : String? = "",
        var telephone       : String? = "",
        var birthday        : String? = "",
        var CCnumber        : String? = "",
        var numberUtent     : String? = "",
        var address         : String? = "",
        var processNumber   : String? = ""
)



class CompleteUserData : AppCompatActivity() {

    val auth = FirebaseAuth.getInstance();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_user_data)

        val user = auth.currentUser
        val btnConfirm = findViewById<Button>(R.id.btnCreate)


        if (user != null) {

            val name = findViewById<TextView>(R.id.inputName)
            val telephone = findViewById<TextView>(R.id.inputPhone)
            val birthday = findViewById<TextView>(R.id.inputBirthday)
            val CCnumber = findViewById<TextView>(R.id.inputCCnumber)
            val numberUtent = findViewById<TextView>(R.id.inputNumberUtent)
            val address = findViewById<TextView>(R.id.inputAddress)
            val processNumber = findViewById<TextView>(R.id.inputProcessNumber)

            btnConfirm.setOnClickListener {

                val database: FirebaseDatabase = FirebaseDatabase.getInstance()
                val myRef: DatabaseReference = database.getReference("Users/${user?.uid}")
                val DATA = User2(
                    name.text.toString(),
                    telephone.text.toString(),
                    birthday.text.toString(),
                    CCnumber.text.toString(),
                    numberUtent.text.toString(),
                    address.text.toString(),
                    processNumber.text.toString()
                )
                myRef.setValue(DATA)
                val intent = Intent(this, WelcomePage::class.java)
                startActivity(intent)
            }
        }


        val btnSkip = findViewById<Button>(R.id.btnSkip)


        btnSkip.setOnClickListener {
            val intent = Intent(this, WelcomePage::class.java)
            startActivity(intent)
        }
    }
}