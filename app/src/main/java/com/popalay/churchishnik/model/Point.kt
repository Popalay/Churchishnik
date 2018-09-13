package com.popalay.churchishnik.model

import com.google.firebase.firestore.GeoPoint

data class Point(
        val index: Int = -1,
        val location: GeoPoint = GeoPoint(0.0, 0.0),
        val description: String = "",
        val imageLink: String = "",
        val answer: String = ""
)