package views

import controllers.AppointmentUIController
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import tornadofx.*

class AddAppointmentScreen : View("Add Appointment") {
    val model = ViewModel()
    val _patient = model.bind { SimpleStringProperty() }
    val _date = model.bind { SimpleStringProperty() }
    val _time = model.bind { SimpleStringProperty() }
    val _price = model.bind { SimpleDoubleProperty() }
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
            field("Time") {
                textfield(_time).required()
            }
            field("Price") {
                textfield(_price).required()
            }
            button("Add") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        appointmentUIController.add(_patient.value, _date.value, _time.value, _price.value)
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
        _time.value = ""
        _price.value = 0.0
        model.clearDecorators()
    }
}
