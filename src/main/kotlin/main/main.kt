package main

import controllers.AppointmentController
import controllers.AppointmentUIController
import tornadofx.launch

fun main(args: Array<String>){
    //AppointmentController().start()
    launch<MainApp>(args)
}