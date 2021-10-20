package views

import controllers.AppointmentUIController
import models.AppointmentModel
import tornadofx.*

class ListAppointmentScreen : View("List All Appointments") {

    val appointmentUIController: AppointmentUIController by inject()
    val tableContent = appointmentUIController.appointments.findAll()
    val data = tableContent.observable()


    override val root = vbox {
        setPrefSize(1000.0, 600.0)
        tableview(data) {
            readonlyColumn("ID", AppointmentModel::id)
            readonlyColumn("PATIENT", AppointmentModel::patient)
            readonlyColumn("DATE", AppointmentModel::date)
            readonlyColumn("TIME", AppointmentModel::time)
            readonlyColumn("PRICE", AppointmentModel::price)
        }
        button("Close") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    appointmentUIController.closeList()
                }
            }
        }
    }
}