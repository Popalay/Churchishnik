package com.popalay.churchishnik.model

import com.google.firebase.firestore.GeoPoint

data class Location(
        val location: GeoPoint = GeoPoint(0.0, 0.0)
)