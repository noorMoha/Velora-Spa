package com.veloraspa.app.data

import com.veloraspa.app.model.Service
import com.veloraspa.app.model.Specialist
import com.veloraspa.app.model.Booking

object MockDataSource {

    //Services:
    val servicesList = listOf(
        Service("1", "Signature Massage",
            "Massage", 90.0,
            "60 min", "Experience ultimate relaxation with our signature treatment."),
        //...........................................
        Service("2", "Rose Facial",
            "Facials", 75.0,
            "45 min", "Nourishing facial treatment using organic rose extracts."),
        //...........................................

        Service("3", "Warm Bamboo Massage",
            "Massage", 110.0,
            "75 min", "A soothing massage using warm bamboo sticks.")
    )
//__________________________________________________________________
    //Specializations:
    val specialistsList = listOf(
        Specialist("1", "Evelyn Vance",
            "Senior Massage Therapist", 4.9,
            "Massage Therapy"),
    //...........................................

    Specialist("2", "Sophia Chen",
        "Skincare Esthetician", 4.8,
        "Facial Treatments"),
    //...........................................

    Specialist("3", "Elena Rostova",
        "Body Care Expert", 4.7,
        "Body Wellness")
    )
//__________________________________________________________________
    //Bookings:
    val bookingsList = mutableListOf(
        Booking("B101", "Signature Massage",
            "Evelyn Vance", "Dec 15, 2026",
            "10:00 AM", "Confirmed"),
    //...........................................

        Booking("B102", "Rose Facial",
            "Sophia Chen", "Dec 18, 2026",
            "02:00 PM", "Pending")
    )
}