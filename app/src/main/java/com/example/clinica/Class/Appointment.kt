package com.example.clinica.Class

class Appointment {
    var speciality  : String? = null
    var medic       : String? = null
    var day         : String? = null
    var month       : String? = null
    var hour        : String? = null
    var confirmed   : String? = null


    constructor(Speciality:String,Medic:String,Day:String,Month:String,Hour:String,Confirmed:String){
        speciality      =   Speciality
        medic           =   Medic
        day             =   Day
        month           =   Month
        hour            =   Hour
        confirmed       =   Confirmed




    }
    constructor(){
    speciality = ""
    medic = ""
    day = ""
    month = ""
    hour = ""
    confirmed = ""

    }



}