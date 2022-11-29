package com.project.aqualife.data

data class AquariumData(
    val name : String,
    val date : String,

    val filtrationRemain : String,
    val filtrationState : String,

    val lightReservation : ArrayList<TemperatureRegulatorSchedule>,
    val lightState : String,

    val co2State : String,
    val co2Reservation : ArrayList<TemperatureRegulatorSchedule>,

    val phWarningMin : String,
    val phWarningMax : String,
    val phValue : String,

    val temperatureHolding : String,
    val temperatureValue : String,

    val filtDayCode : String,
    val filtAmount : String,
    val filtStartTime : String,
    val filtRemain: String
    )