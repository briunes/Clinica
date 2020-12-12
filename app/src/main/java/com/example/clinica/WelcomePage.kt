package com.example.clinica

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.clinica.R.id.*
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth


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


            Toast.makeText(applicationContext,"${user.uid}" ,Toast.LENGTH_SHORT).show()



            toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView.setNavigationItemSelectedListener{
                when(it.itemId) {
                    action_settings -> Toast.makeText(applicationContext,"Settings", Toast.LENGTH_SHORT).show()
                    action_contacts -> Toast.makeText(applicationContext,"Contacts", Toast.LENGTH_SHORT).show()
                    action_logout -> FirebaseAuth.getInstance().signOut()
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
                val intent = Intent(this, GetReceipts::class.java)
                startActivity(intent)
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