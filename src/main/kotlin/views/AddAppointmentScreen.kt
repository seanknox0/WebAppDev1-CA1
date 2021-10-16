package views

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import controllers.AppointmentUIController
import tornadofx.*
import kotlin.reflect.KClass

class AddAppointmentScreen : View("Add Appointment") {
    val model = ViewModel()
    val _patient = model.bind { SimpleStringProperty() }
    val _date = model.bind { SimpleStringProperty() }
    val appointmentUIController: AppointmentUIController by inject()

    override val root = form {
        setPrefSize(600.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Patient") {
                textfield(_patient).required()
            }
            field("Date") {
                textarea(_date).required()
            }
            button("Add") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        appointmentUIController.add(_patient.toString(), _date.toString())

                    }
                }
            }
            button("Close") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        appointmentUIController.closeAdd()
                    }
                }
            }
        }
    }

    override fun onDock() {
        _patient.value = ""
        _date.value = ""
        model.clearDecorators()
    }
}
