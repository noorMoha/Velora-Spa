package com.veloraspa.app.model

data class Booking(
    val bookingId: String,
    val serviceName: String,
    val specialistName: String,
    val date: String,
    val time: String,
    val status: String
)