package views

import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.scene.control.TableView
import javafx.scene.layout.GridPane
import controllers.AppointmentUIController
import models.AppointmentModel
import tornadofx.*

class ListAppointmentScreen : View("List All Appointments") {

    val appointmentUIController: AppointmentUIController by inject()
    val tableContent = appointmentUIController.appointments.findAll()
    val data = tableContent.observable()


    override val root = vbox {
        setPrefSize(600.0, 200.0)
        tableview(data) {
            readonlyColumn("ID", AppointmentModel::id)
            readonlyColumn("TITLE", AppointmentModel::patient)
            readonlyColumn("DESCRIPTION", AppointmentModel::date)
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