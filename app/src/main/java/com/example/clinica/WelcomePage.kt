package com.example.clinica

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.clinica.R.id.*
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

data class especialide(
        var name            : String? = "",

)
class WelcomePage : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle
    val auth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)

        val user = auth.currentUser
        if (user != null) {

            // Initialize variables
            val btnTalkMedic = findViewById<Button>(btn_Talk_Medic)
            val btnGetDocs = findViewById<Button>(btn_get_Documents)
            val btnGetAppointments = findViewById<Button>(btn_get_appointments)
            val btnGetReceits = findViewById<Button>(btn_get_receipts)
            val drawerLayout = findViewById<DrawerLayout>(drawerLayout)
            val navView = findViewById<NavigationView>(navView)
            val drawerToggleBtn = findViewById<Button>(btn_pull_drawer)
            val txtEmail = findViewById<TextView>(textViewEmail)
            val txtName = findViewById<TextView>(textViewName)

            val database: FirebaseDatabase = FirebaseDatabase.getInstance()
            val myRef: DatabaseReference = database.getReference("Users/${user.uid}")





            toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView.setNavigationItemSelectedListener{
                when(it.itemId) {
                    R.id.action_contacts ->{

                        val intent = Intent(this, Contacts::class.java)
                        startActivity(intent)
                    }
                    R.id.action_settings ->{
                        val intent = Intent(this, Contacts::class.java)
                        startActivity(intent)
                    }
                    R.id.action_logout->{
                        FirebaseAuth.getInstance().signOut()
                        val intent = Intent(this, Login::class.java )
                        startActivity(intent)
                    }

                }
                true

            }

            //Button that opens drawer
            drawerToggleBtn.setOnClickListener{
                drawerLayout.openDrawer(Gravity.LEFT)
            }

            //Send to other pages
            btnGetAppointments.setOnClickListener{
                val intent = Intent(this, GetAppointments::class.java)
                startActivity(intent)
            }
            btnTalkMedic.setOnClickListener{
                val intent = Intent(this, ChatMedic::class.java)
                startActivity(intent)
            }
            btnGetDocs.setOnClickListener{
                val intent = Intent(this, GetDocuments::class.java)
                startActivity(intent)
            }
            btnGetReceits.setOnClickListener{


               /* val database: FirebaseDatabase = FirebaseDatabase.getInstance()
                val myRef: DatabaseReference = database.getReference("Specialties")

                val Dados = especialide("Neurologia")
                val Dados1 = especialide("Pneumologia")
                val Dados2 = especialide("Medicina física")
                val Dados3 = especialide("Reabilitação")
                val Dados4 = especialide("Cuidados paliativos")
                myRef.push().setValue(Dados)
                myRef.push().setValue(Dados1)
                myRef.push().setValue(Dados2)
                myRef.push().setValue(Dados3)
                myRef.push().setValue(Dados4)*/
            }



        }


                //in case user is logged out goes to main page
        else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item))
        {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}