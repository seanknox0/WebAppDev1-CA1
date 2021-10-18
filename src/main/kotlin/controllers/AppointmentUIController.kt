package controllers

import DeleteAppointmentScreen
import SearchAppointmentScreen
import UpdateAppointmentScreen
import main.MainApp
import mu.KotlinLogging
import models.AppointmentJSONStore
import models.AppointmentModel
import tornadofx.*
import views.*

class AppointmentUIController : Controller() {

    val appointments = AppointmentJSONStore()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Clinic Appointment TornadoFX UI App" }
    }

    fun add(_patient : String, _date : String){

        var aAppointment = AppointmentModel(patient = _patient, date = _date)
        appointments.create(aAppointment)
        logger.info("Appointment Added")
    }

    fun update(_id: String, _patient: String, _date: String){
        var aId = getId(_id)
        var appointmentChange = search(aId)

        if(appointmentChange != null) {
            appointmentChange.patient = _patient
            appointmentChange.date = _date
            logger.info("Appointment Updated : [ $appointmentChange ]")
        }
        else {
            logger.info("Appointment Not Updated")
        }
        appointments.logAll()
    }

    fun delete(_id: String){
        var aId = getId(_id)
        var aAppointment = search(aId)

        if(aAppointment != null) {
            appointments.delete(aAppointment)
            println("Appointment Deleted...")
            appointments.logAll()
        }
        else
            println("Appointment Not Deleted...")
    }

    fun getId( _id : String): Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        strId = _id
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }

    fun search(_id : String) : AppointmentModel {
        val aAppointment = search(getId(_id))!!
        println(aAppointment)
        showAppointment(aAppointment)
        return aAppointment
    }

    fun search(id: Long) : AppointmentModel? {
        var foundAppointment = appointments.findOne(id)
        return foundAppointment
    }

    fun showAppointment(appointment : AppointmentModel) {
        if(appointment != null)
            println("Appointment Details [ $appointment ]")
        else
            println("Appointment Not Found...")
    }

    fun loadListScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(ListAppointmentScreen::class, sizeToScene = true, centerOnScreen = true)
        }
        appointments.logAll()
    }

    fun loadAddScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(AddAppointmentScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun loadUpdateScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(UpdateAppointmentScreen::class, sizeToScene = true, centerOnScreen = true)
        }
        appointments.logAll()
    }

    fun loadDeleteScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(DeleteAppointmentScreen::class, sizeToScene = true, centerOnScreen = true)
        }
        appointments.logAll()
    }

    fun loadSearchScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(SearchAppointmentScreen::class, sizeToScene = true, centerOnScreen = true)
        }
        appointments.logAll()
    }

    fun closeAdd() {
        runLater {
            find(AddAppointmentScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeUpdate() {
        runLater {
            find(UpdateAppointmentScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeDelete() {
        runLater {
            find(DeleteAppointmentScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeSearch() {
        runLater {
            find(SearchAppointmentScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeList() {
        runLater {
            find(ListAppointmentScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

}