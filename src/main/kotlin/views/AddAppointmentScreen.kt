package views

import controllers.AppointmentUIController
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import tornadofx.*
import tornadofx.Stylesheet.Companion.form
import kotlin.reflect.KClass

class AddAppointmentScreen : View("Add Appointment") {
    val model = ViewModel()
    val _patient = model.bind { SimpleStringProperty() }
    val _date = model.bind { SimpleStringProperty() }
    val appointmentUIController: AppointmentUIController by inject()

    override val root = form {
        setPrefSize(1000.0, 600.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Patient") {
                textfield(_patient).required()
            }
            field("Date") {
                textfield(_date).required()
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
