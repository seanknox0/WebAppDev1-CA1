package controllers

import mu.KotlinLogging
import models.AppointmentJSONStore
import models.AppointmentModel
import views.AddAppointmentScreen
import views.ListAppointmentScreen
import views.MenuScreen
import tornadofx.*

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

    fun closeAdd() {
        runLater {
            find(AddAppointmentScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun closeList() {
        runLater {
            find(ListAppointmentScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

}