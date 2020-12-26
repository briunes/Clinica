package com.example.clinica

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.clinica.Class.Appointment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class GetAppointments : AppCompatActivity() {
    var appointments: ArrayList<Appointment> = ArrayList<Appointment>()

    var adapter = AppointmentsAdapter()
    val auth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_appointments)
        setSupportActionBar(findViewById(R.id.toolbar))

        val user = auth.currentUser
        if (user == null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference("Users/${user?.uid}/Appointments/")

        val ListviewAppointments = findViewById<ListView>(R.id.listviewAppointments)

        ListviewAppointments.adapter = adapter

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                appointments.clear()

                for (productSnapshot in dataSnapshot.children) {
                    val appointment = productSnapshot.getValue(Appointment::class.java)
                    appointments.add(appointment!!)
                }
                Toast.makeText(applicationContext, "${appointments.size}", Toast.LENGTH_SHORT)  .show()

                if(appointments.isEmpty())      Toast.makeText(applicationContext, "Não tem nenhuma consulta marcada", Toast.LENGTH_SHORT)  .show()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Failed to read value.", Toast.LENGTH_SHORT)  .show()
            }
        })


        if(appointments != null)
        {
            adapter.notifyDataSetChanged()
        }

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            val intent = Intent(this, CreateAppointment::class.java)
            startActivity(intent)
        }
    }

    inner class AppointmentsAdapter : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = layoutInflater.inflate(R.layout.row_appointment, parent, false)
            val txtspeciality = findViewById<TextView>(R.id.txtSpeciality)
            val txtConfirmed = findViewById<TextView>(R.id.txtConfirmed)
            val txtDate = findViewById<TextView>(R.id.txtDate)

            val appointment = appointments[position]

            txtspeciality.text = appointment.speciality
            txtConfirmed.text = appointment.confirmed
            txtDate.text = appointment.hour.toString() + "/" + appointment.month.toString()

            Toast.makeText(applicationContext, "${appointment.speciality}", Toast.LENGTH_SHORT)  .show()

            return view
        }

        override fun getCount(): Int {
            return appointments.size
        }

        override fun getItem(position: Int): Any {
            return appointments[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }
    }
}