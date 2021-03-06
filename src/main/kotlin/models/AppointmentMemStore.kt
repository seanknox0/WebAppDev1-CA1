package models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class AppointmentMemStore : AppointmentStore {

    val appointments = ArrayList<AppointmentModel>()

    override fun findAll(): List<AppointmentModel> {
        return appointments
    }

    override fun findOne(id: Long): AppointmentModel? {
        var foundAppointment: AppointmentModel? = appointments.find { p -> p.id == id }
        return foundAppointment
    }

    override fun create(appointment: AppointmentModel) {
        appointment.id = getId()
        appointments.add(appointment)
        logAll()
    }

    override fun delete(appointment: AppointmentModel) {
        appointments.remove(appointment)
    }

    override fun update(appointment: AppointmentModel) {
        var foundAppointment = findOne(appointment.id!!)
        if (foundAppointment != null) {
            foundAppointment.patient = appointment.patient
            foundAppointment.date = appointment.date
            foundAppointment.time = appointment.time
            foundAppointment.price = appointment.price
        }
    }

    internal fun logAll() {
        appointments.forEach { logger.info("${it}") }
    }
}
