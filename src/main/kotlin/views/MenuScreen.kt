package views

import javafx.application.Platform
import javafx.geometry.Orientation
import controllers.AppointmentUIController
import tornadofx.*

class MenuScreen : View("Clinic Appointment Main Menu") {

    val appointmentUIController: AppointmentUIController by inject()

    override val root = form {
        setPrefSize(1000.0, 600.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Add Appointment") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        appointmentUIController.loadAddScreen()
                    }
                }
            }
            text("")
            button("Update Appointment") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        appointmentUIController.loadUpdateScreen()
                    }
                }
            }
            text("")
            button("List All Appointments") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        appointmentUIController.loadListScreen()
                    }
                }
            }
            text("")
            button("Delete Appointment") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        appointmentUIController.loadDeleteScreen()
                    }
                }
            }
            text("")
            button("Search Appointment (Using ID)") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        appointmentUIController.loadSearchScreen()
                    }
                }
            }
            text("")
            button("Exit") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        Platform.exit();
                        System.exit(0);
                    }
                }
            }
        }

    }
}