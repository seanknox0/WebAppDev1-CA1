package models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import helpers.*
import java.util.*
import kotlin.math.absoluteValue

private val logger = KotlinLogging.logger {}

val JSON_FILE = "appointments.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<AppointmentModel>>() {}.type

fun generateRandomId(): Long {
    val randomId = Math.abs(Random().nextLong())
    val genId = randomId / 100000000000000;
    return genId
}

class AppointmentJSONStore : AppointmentStore {

    var appointments = mutableListOf<AppointmentModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<AppointmentModel> {
        return appointments
    }

    override fun findOne(id: Long) : AppointmentModel? {
        var foundAppointment: AppointmentModel? = appointments.find { p -> p.id == id }
        return foundAppointment
    }

    override fun create(appointment: AppointmentModel) {
        appointment.id = generateRandomId()
        appointments.add(appointment)
        serialize()
    }

    override fun delete(appointment: AppointmentModel) {
        appointments.remove(appointment)
        serialize()
    }

    override fun update(appointment: AppointmentModel) {
        var foundAppointment = findOne(appointment.id!!)
        if (foundAppointment != null) {
            foundAppointment.patient = appointment.patient
            foundAppointment.date = appointment.date
            foundAppointment.time = appointment.time
            foundAppointment.price = appointment.price
        }
        serialize()
    }

    internal fun logAll() {
        appointments.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(appointments, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        appointments = Gson().fromJson(jsonString, listType)
    }
}