package com.example.clinica

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class Register : AppCompatActivity() {

    val auth = FirebaseAuth.getInstance();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val email = findViewById<EditText>(R.id.inputEmail)
        val password = findViewById<EditText>(R.id.inputPassword)
        val passwordConfirm = findViewById<EditText>(R.id.inputPasswordConfirm)
        val btnRegister = findViewById<Button>(R.id.btn_register)

        btnRegister.setOnClickListener{

            if (email.text.toString() == "" || password.text.toString() == ""|| passwordConfirm.text.toString() == "")
            {
                Toast.makeText(this, "Credencias Vazias", Toast.LENGTH_SHORT).show()
            }

                else if(password.text.toString() != passwordConfirm.text.toString())
                {
                    Toast.makeText(this,"As Palavras pass não são iguais",Toast.LENGTH_SHORT).show()
                }
                    else if(password.text.toString().length < 6 || passwordConfirm.text.toString().length > 12)
                    {
                        Toast.makeText(this,"Password tem que ter entre 6 a 12 caracteres",Toast.LENGTH_SHORT).show()
                    }
                        else
                        {
                            auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                                .addOnCompleteListener(this) { task ->
                                    if (task.isSuccessful) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(baseContext, "Register success.", Toast.LENGTH_SHORT).show()
                                        val intent = Intent(this, WelcomePage::class.java)
                                        startActivity(intent)
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(baseContext, "register failed.", Toast.LENGTH_SHORT).show()

                                    }
                                }

                        }

            }
    }

}