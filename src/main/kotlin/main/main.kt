package main

import models.AppointmentModel
import mu.KotlinLogging
import java.awt.SystemColor.menu

private val logger = KotlinLogging.logger {}

var appointment = AppointmentModel()
val appointments = ArrayList<AppointmentModel>()

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

fun makeAppointment(){
    var aAppointment = AppointmentModel()
    println("Add Appointment")
    println()
    print("Enter Patient Name : ")
    aAppointment.patient = readLine()!!
    print("Enter a Date : ")
    aAppointment.date = readLine()!!

    if (aAppointment.patient.isNotEmpty() && aAppointment.date.isNotEmpty()) {
        aAppointment.id = appointments.size.toLong()
        appointments.add(aAppointment.copy())
        logger.info("Appointment Added : [ $aAppointment ]")
    }
    else
        logger.info("Appointment Not Added")
}

fun updateAppointment() {
    println("Update Appointment")
    println()
    listAllAppointments()
    var searchId = getId()
    val aAppointment = search(searchId)
    var tempPatient : String?
    var tempDate : String?

    if(aAppointment != null) {
        print("Enter a new Patient for [ " + aAppointment.patient + " ] : ")
        tempPatient = readLine()!!
        print("Enter a new Date for [ " + aAppointment.date + " ] : ")
        tempDate = readLine()!!

        if (!tempPatient.isNullOrEmpty() && !tempDate.isNullOrEmpty()) {
            aAppointment.patient = tempPatient
            aAppointment.date = tempDate
            println(
                "You updated [ " + aAppointment.patient+ " ] for patient " +
                        "and [ " + aAppointment.date + " ] for date")
            logger.info("Appointment Updated : [ $aAppointment ]")
        }
        else
            logger.info("Appointment Not Updated")
    }
    else
        println("Appointment Not Updated...")
}

fun listAllAppointments() {
    println("List All Appointments")
    println()
    appointments.forEach { logger.info("${it}")
        println()
    }
}

fun searchAppointments() {

    var searchId = getId()
    val aAppointment = search(searchId)

    if(aAppointment != null)
        println("Appointment Details [ $aAppointment ]")
    else
        println("Appointment Not Found...")
}

fun getId() : Long {
    var strId : String?
    var searchId : Long
    print("Enter id to Search/Update : ")
    strId = readLine()!!
    searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
        strId.toLong()
    else
        -9
    return searchId
}

fun search(id: Long) : AppointmentModel? {
    var foundAppointment: AppointmentModel? = appointments.find { p -> p.id == id }
    return foundAppointment
}


fun menu() : Int {

    var option: Int
    var input: String? = null

    println("Main Menu")
    println(" 1. Make Appointment")
    println(" 2. Update Appointment")
    println(" 3. List All Appointments")
    println(" 4. Search Appointments")
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

fun dummyData() {
    appointments.add(AppointmentModel(1, "Jim Jimmy", "20-03-21"))
    appointments.add(AppointmentModel(2, "Joe Joey", "12-06-21"))
    appointments.add(AppointmentModel(3, "Sarah Kelly", "05-04-21"))
}