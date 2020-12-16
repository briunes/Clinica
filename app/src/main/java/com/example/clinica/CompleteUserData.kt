package com.example.clinica

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.clinica.Class.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue


class CompleteUserData : AppCompatActivity() {

    val auth = FirebaseAuth.getInstance();
    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_user_data)

        val user = auth.currentUser
        val btnConfirm = findViewById<Button>(R.id.btnCreate)
        val myRef: DatabaseReference = database.getReference("Users/${user?.uid}/Data")

        val database: FirebaseDatabase = FirebaseDatabase.getInstance()



        if (user != null) {

            val name = findViewById<TextView>(R.id.inputName)
            val telephone = findViewById<TextView>(R.id.inputPhone)
            val birthday = findViewById<TextView>(R.id.inputBirthday)
            val CCnumber = findViewById<TextView>(R.id.inputCCnumber)
            val numberUtent = findViewById<TextView>(R.id.inputNumberUtent)
            val address = findViewById<TextView>(R.id.inputAddress)
            val processNumber = findViewById<TextView>(R.id.inputProcessNumber)
            val btnConfirm = findViewById<Button>(R.id.btnCreate)
            val checkData : User


            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val checkData = dataSnapshot.getValue<User>()

                    if (checkData != null) setContentView(R.layout.activity_welcome_page);

                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext, "Failed to read value.", Toast.LENGTH_SHORT).show()
                }
            })


            btnConfirm.setOnClickListener {


                val DATA = User(
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


    }
}