package controllers

import mu.KotlinLogging
import models.AppointmentMemStore
import models.AppointmentModel
import views.AppointmentView

class AppointmentController {

    val appointments = AppointmentMemStore()
    val appointmentView = AppointmentView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Clinic Appointment Console App" }
        println("Appointment Kotlin App Version 3.0")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when(input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                -99 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Clinic Appointment Console App" }
    }

    fun menu() :Int { return appointmentView.menu() }

    fun add(){
        var aAppointment = AppointmentModel()

        if (appointmentView.addAppointmentData(aAppointment))
            appointments.create(aAppointment)
        else
            logger.info("Appointment Not Added")
    }

    fun list() {
        appointmentView.listAppointments(appointments)
    }

    fun update() {
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

    fun search() {
        val aAppointment = search(appointmentView.getId())!!
        appointmentView.showAppointment(aAppointment)
    }


    fun search(id: Long) : AppointmentModel? {
        var foundAppointment = appointments.findOne(id)
        return foundAppointment
    }

    fun dummyData() {
        appointments.create(AppointmentModel(patient = "Joe Joey", date = "12-03-21"))
        appointments.create(AppointmentModel(patient= "Ring of Kerry", date = "10-04-21"))
        appointments.create(AppointmentModel(patient = "Waterford City", date = "06-04-21"))
    }
}