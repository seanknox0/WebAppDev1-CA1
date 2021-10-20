package models

data class AppointmentModel(var id: Long = 0,
                            var patient: String = "",
                            var date: String = "",
                            var time: String = "",
                            var price: Double = 0.0)