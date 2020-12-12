package com.example.clinica

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class GetAppointments : AppCompatActivity() {
    var appointments: MutableList<Appointment> = ArrayList()
    var adapter = ArticlesAdapter()
    val auth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_appointments)
        setSupportActionBar(findViewById(R.id.toolbar))

        val user = auth.currentUser
        if (user != null) {

        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference("Appointments")

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->

            val intent = Intent(this, CreateAppointment::class.java)
            startActivity(intent)
        }


        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val appointments = dataSnapshot.getValue(Appointment::class.java)


                Toast.makeText(applicationContext,"Value is: ${appointments}}" ,Toast.LENGTH_SHORT).show()


            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Failed to read value.", Toast.LENGTH_SHORT)
                        .show()
            }
        })

}

    inner class ArticlesAdapter : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = layoutInflater.inflate(R.layout.row_appointment, parent, false)
            val txtspeciality = findViewById<TextView>(R.id.txtSpeciality)
            val txtConfirmed = findViewById<TextView>(R.id.txtConfirmed)
            val txtDate = findViewById<TextView>(R.id.txtDate)

            val appointment = appointments.get(position)

            txtspeciality.text = appointment.speciality
            txtConfirmed.text = appointment.confirmed.toString()
            txtDate.text = appointment.hour.toString() + "/" + appointment.month.toString()

            return view
        }

        override fun getCount(): Int {
            return appointments.size
        }

        override fun getItem(position: Int): Any {
            return appointments.get(position)        }

        override fun getItemId(position: Int): Long {
            return 0
        }
    }
}