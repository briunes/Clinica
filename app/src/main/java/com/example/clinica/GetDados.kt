package com.example.clinica

import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*


data class User(
    var username: String? = "",
    var email: String? = ""
)

class GetDados : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_dados)

        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference("users")

        val dados = findViewById<ListView>(R.id.listviewDados)

        //val user = User("bruno", "email")
        //myRef.child("users").child("2").setValue(user)


        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {



                val products: ArrayList<User?> = ArrayList<User?>()
                for (productSnapshot in dataSnapshot.children) {
                    val product: User? = productSnapshot.getValue(User::class.java)
                    products.add(product)
                }
                System.out.println(products)


                Toast.makeText(applicationContext,"Value is: ${products.size}" ,Toast.LENGTH_SHORT).show()

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Toast.makeText(applicationContext, "Failed to read value.", Toast.LENGTH_SHORT)
                    .show()

            }
        })

    }
    }
