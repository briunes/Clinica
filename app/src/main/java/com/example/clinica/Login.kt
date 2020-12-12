package com.example.clinica

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class Login : AppCompatActivity() {

    companion object {
        val TAG = "Login"
        val RC_SIGN_IN = 1001
    }
    val auth = FirebaseAuth.getInstance();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        val btnLogin = findViewById<Button>(R.id.btn_register)
        val email = findViewById<TextView>(R.id.inputEmail)
        val password = findViewById<TextView>(R.id.inputPassword)
        val passwordConfirm = findViewById<TextView>(R.id.inputPasswordConfirm)


        btnLogin.setOnClickListener{

            auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information

                        auth.currentUser

                        Toast.makeText(baseContext, "Authentication success.${auth.currentUser?.email.toString()}}",
                            Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, WelcomePage::class.java)
                        startActivity(intent)


                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()

                    }
                }


        }
    }
}