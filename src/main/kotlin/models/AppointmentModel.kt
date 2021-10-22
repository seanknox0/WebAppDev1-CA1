package models

import kotlin.math.absoluteValue

data class AppointmentModel(var id: Long = 0,
                            var patient: String = "",
                            var date: String = "",
                            var time: String = "",
                            var price: Double = 0.0){
    init {
        require(patient.length <= 25)
        { "Patient out of bounds" }

        require(date.length <= 8)
        { "Date out of bounds" }

        require(time.length <= 5)
        { "Time out of bounds" }

        require(price >= 0.0)
        { "Price out of bounds" }
    }
}