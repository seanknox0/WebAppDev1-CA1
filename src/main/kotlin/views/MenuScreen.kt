package views

import javafx.application.Platform
import javafx.geometry.Orientation
import controllers.AppointmentUIController
import tornadofx.*

class MenuScreen : View("Clinic Appointment Main Menu") {

    val appointmentUIController: AppointmentUIController by inject()

    override val root = form {
        setPrefSize(400.0, 200.0)
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