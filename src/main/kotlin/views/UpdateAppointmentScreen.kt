import controllers.AppointmentUIController
import javafx.beans.property.SimpleLongProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import models.AppointmentModel
import tornadofx.*

class UpdateAppointmentScreen : View("Update Appointment") {
    val model = ViewModel()
    val _id = model.bind { SimpleLongProperty() }
    val _patient = model.bind { SimpleStringProperty() }
    val _date = model.bind { SimpleStringProperty() }
    val appointmentUIController: AppointmentUIController by inject()
    val tableContent = appointmentUIController.appointments.findAll()
    val data = tableContent.observable()

    override val root = form {
        setPrefSize(1000.0, 600.0)
        tableview(data) {
            readonlyColumn("ID", AppointmentModel::id)
            readonlyColumn("PATIENT", AppointmentModel::patient)
            readonlyColumn("DATE", AppointmentModel::date)
        }
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("ID") {
                textfield(_id).required()
            }
            field("New Patient") {
                textfield(_patient).required()
            }
            field("New Date") {
                textfield(_date).required()
            }
            button("Update") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        appointmentUIController.update(_id.toString(), _patient.toString(), _date.toString())
                    }
                }
            }
            button("Close") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        appointmentUIController.closeUpdate()
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