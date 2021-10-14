package main

import models.AppointmentModel
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

var appointment = AppointmentModel()

fun main(args: Array<String>){
    logger.info { "Launching Clinic Appointment Console App" }
    println("Clinic Appointment Kotlin App Version 2.0")

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> makeAppointment()
            2 -> updateAppointment()
            3 -> listAllAppointments()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Clinic Appointment Console App" }
}

fun makeAppointment() {
    println("Make Appointment")
    println()
    print("Enter a Patient : ")
    appointment.patient = readLine()!!
    print("Enter a Date : ")
    appointment.date = readLine()!!
    println("You entered [ " + appointment.patient + " ] for patient" +
            " and [ " + appointment.date + " ] for date")
}

fun updateAppointment() {
    println("Update Placemark")
    println()
    print("Enter a new Title for [ " + appointment.patient + " ] : ")
    appointment.patient = readLine()!!
    print("Enter a new Description for [ " + appointment.date + " ] : ")
    appointment.date = readLine()!!
    println("You updated [ " + appointment.patient + " ] for title " +
            "and [ " + appointment.date + " ] for description")
}

fun listAllAppointments() {
    println("You Chose List All Appointments")
}

fun menu() : Int {

    var option : Int
    var input: String? = null

    println("Main Menu")
    println(" 1. Make Appointment")
    println(" 2. Update Appointment")
    println(" 3. List All Appointments")
    println("-1. Exit")
    println()
    print("Enter an integer : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}