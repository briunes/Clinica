package com.example.clinica.Class

class User {

    var name            : String? = null
    var telephone       : String? = null
    var birthday        : String? = null
    var CCnumber        : String? = null
    var numberUtent     : String? = null
    var address         : String? = null
    var processNumber   : String? = null

    constructor(
        name: String?,
        telephone: String?,
        birthday: String?,
        CCnumber: String?,
        numberUtent: String?,
        address: String?,
        processNumber: String?,
    ) {
        this.name = name
        this.telephone = telephone
        this.birthday = birthday
        this.CCnumber = CCnumber
        this.numberUtent = numberUtent
        this.address = address
        this.processNumber = processNumber
    }

    constructor() {
        this.name = ""
        this.telephone = ""
        this.birthday = ""
        this.CCnumber = ""
        this.numberUtent = ""
        this.address = ""
        this.processNumber = ""
    }

}