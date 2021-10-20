package views

import models.AppointmentJSONStore
import models.AppointmentMemStore
import models.AppointmentModel

class AppointmentView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println("MAIN MENU")
        println(" 1. Make Appointment")
        println(" 2. Update Appointment")
        println(" 3. List All Appointments")
        println(" 4. Delete Appointment")
        println(" 5. Search Appointments")
        println("-1. Exit")
        println()
        print("Enter Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listAppointments(appointments : AppointmentJSONStore) {
        println("List All Appointments")
        println()
        appointments.logAll()
        println()
    }

    fun showAppointment(appointment : AppointmentModel) {
        if(appointment != null)
            println("Appointment Details [ $appointment ]")
        else
            println("Appointment Not Found...")
    }

    fun addAppointmentData(appointment : AppointmentModel) : Boolean {
        println()
        print("Enter a Patient : ")
        appointment.patient = readLine()!!
        print("Enter a Date : ")
        appointment.date = readLine()!!
        print("Enter a Time : ")
        appointment.time = readLine()!!
        print("Enter a Price : ")
        appointment.price = readLine()!!.toDouble()

        return appointment.patient.isNotEmpty() && appointment.date.isNotEmpty()
                && appointment.time.isNotEmpty() && appointment.price.isNaN()
    }

    fun updateAppointmentData(appointment : AppointmentModel) : Boolean {

        var tempPatient: String?
        var tempDate: String?
        var tempTime: String?
        var tempPrice: Double?

        if (appointment != null) {
            print("Enter a new Patient Name for [ " + appointment.patient + " ] : ")
            tempPatient = readLine()!!
            print("Enter a new Date for [ " + appointment.date + " ] : ")
            tempDate = readLine()!!
            print("Enter a new Time for [ " + appointment.time + " ] : ")
            tempTime = readLine()!!
            print("Enter a new Price for [ " + appointment.price + " ] : ")
            tempPrice = readLine()!!.toDouble()

            if (!tempPatient.isNullOrEmpty() && !tempDate.isNullOrEmpty()
                && !tempTime.isNullOrEmpty() && !tempPrice.isNaN()) {
                appointment.patient = tempPatient
                appointment.date = tempDate
                appointment.time = tempTime
                appointment.price = tempPrice
                return true
            }
        }
        return false
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id to Search/Update : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}