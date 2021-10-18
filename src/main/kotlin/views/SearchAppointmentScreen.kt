import controllers.AppointmentUIController
import javafx.beans.property.SimpleLongProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import models.AppointmentModel
import tornadofx.*

class SearchAppointmentScreen : View("Search Appointment") {
    val model = ViewModel()
    val _id = model.bind { SimpleLongProperty() }
    val _patient = model.bind { SimpleStringProperty() }
    val _date = model.bind { SimpleStringProperty() }
    val appointmentUIController: AppointmentUIController by inject()
    val tableContent = appointmentUIController.appointments.findAll()
    val data = tableContent.observable()

    override val root = form {
        setPrefSize(1000.0, 600.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            tableview(data) {
                readonlyColumn("ID", AppointmentModel::id)
                readonlyColumn("PATIENT", AppointmentModel::patient)
                readonlyColumn("DATE", AppointmentModel::date)
            }
            field("ID") {
                textfield(_id).required()
            }
            button("Search") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        appointmentUIController.search(_id.toString())
                    }
                }
            }
            button("Close") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        appointmentUIController.closeSearch()
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