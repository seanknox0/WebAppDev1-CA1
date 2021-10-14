package main

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

fun main(args: Array<String>){
    logger.info { "Launching Clinic Appointment Console App" }
    println("Clinic Appointment Kotlin App Version 1.0")

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
    println("You Chose Make Appointment")
}

fun updateAppointment() {
    println("You Chose Update Appointment")
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