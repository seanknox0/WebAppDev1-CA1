package main

import models.AppointmentMemStore
import models.AppointmentModel
import mu.KotlinLogging
import views.AppointmentView

private val logger = KotlinLogging.logger {}

val appointments = AppointmentMemStore()
val appointmentView = AppointmentView()

fun main(args: Array<String>){
    logger.info { "Launching Clinic Appointment Console App" }
    println("Clinic Appointment Kotlin App Version 3.0")

    var input: Int

    do {
        input = appointmentView.menu()
        when(input) {
            1 -> makeAppointment()
            2 -> updateAppointment()
            3 -> appointmentView.listAppointments(appointments)
            4 -> searchAppointments()
            -99 -> dummyData()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Clinic Appointment Console App" }
}

fun makeAppointment(){
    var aAppointment = AppointmentModel()

    if (appointmentView.addAppointmentData(aAppointment))
        appointments.create(aAppointment)
    else
        logger.info("Appointment Not Added")
}

fun updateAppointment() {
    appointmentView.listAppointments(appointments)
    var searchId = appointmentView.getId()
    val aAppointment = search(searchId)

    if(aAppointment != null) {
        if(appointmentView.updateAppointmentData(aAppointment)) {
            appointments.update(aAppointment)
            appointmentView.showAppointment(aAppointment)
            logger.info("Appointment Updated : [ $aAppointment ]")
        }
        else
            logger.info("Appointment Not Updated")
    }
    else
        println("Appointment Not Updated...")
}


fun searchAppointments() {
    val aAppointment = search(appointmentView.getId())!!
    appointmentView.showAppointment(aAppointment)
}


fun search(id: Long) : AppointmentModel? {
    var foundAppointment = appointments.findOne(id)
    return foundAppointment
}


fun dummyData() {
    appointments.create(AppointmentModel(1, "Jim Jimmy", "20-03-21"))
    appointments.create(AppointmentModel(2, "Joe Joey", "12-06-21"))
    appointments.create(AppointmentModel(3, "Sarah Kelly", "05-04-21"))
}