package com.example.clinica

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.clinica.Class.Appointment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*



class CreateAppointment : AppCompatActivity() {

    val auth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_appointment)

        val user = auth.currentUser
        if (user != null) {

        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        //dados estaticos de especialidades
        val specialtys = arrayOf<String>(
            "Especialidade 1",
            "Especialidade 2",
            "Especialidade 3",
            "Especialidade 4"
        )

        val btnCreate = findViewById<Button>(R.id.btn_create_appointment)
        //É a dropdown list com especialidades
        val spinner = findViewById<Spinner>(R.id.spinnerSpecialty)
        //Bottao que abre o relogio
        val btnTime = findViewById<Button>(R.id.btn_time)
        //textview que demonstra as horas marcadas
        val txtTime = findViewById<TextView>(R.id.txtTime)
        //Calendario
        val calender = findViewById<CalendarView>(R.id.calendarViewDay)
        //Spinner
        val cal = Calendar.getInstance()
        //Date
        var apointmentday: String? = ""
        var apointmentMonth: String? = ""
        //Base de dados Firebase
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference("Users/${user?.uid}/Appointments")

        //Open Dropdown list
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, specialtys
            )
            spinner.adapter = adapter

        }

        //Open Clock
        btnTime.setOnClickListener{

            val timeSetListener = TimePickerDialog.OnTimeSetListener{ view: TimePicker?, hourOfDay: Int, minute: Int ->
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
                cal.set(Calendar.MINUTE, minute)

                txtTime.text = SimpleDateFormat("HH:mm").format(cal.time)

            }
            TimePickerDialog(
                this,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()
        }

        calender.setOnDateChangeListener { view, year, month, dayOfMonth ->
            apointmentday = dayOfMonth.toString()
            apointmentMonth = month.toString()
        }
        btnCreate.setOnClickListener {
            val Apointment = Appointment(spinner.getSelectedItem().toString(), "123456789", apointmentday.toString(),apointmentMonth.toString(), SimpleDateFormat("HH:mm").format(cal.time),
            "false")
            myRef.push().setValue(Apointment).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext, "Consulta Pedida para dia.${Apointment.day}/${Apointment.month} ás ${Apointment.hour} ",
                            Toast.LENGTH_LONG).show()

                    val intent = Intent(this, WelcomePage::class.java)
                    startActivity(intent)
                }
            }


        }


    }
}