package models

interface AppointmentStore {
    fun findOne(id: Long): AppointmentModel?
    fun create(appointment: AppointmentModel)
    fun update(appointment: AppointmentModel)
    fun findAll(): List<AppointmentModel>
}