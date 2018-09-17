package com.popalay.churchishnik.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.Query
import com.popalay.churchishnik.model.Location
import com.popalay.churchishnik.model.Message
import com.popalay.churchishnik.model.Point
import kotlin.properties.Delegates

object Api {

    private var context: Context by Delegates.notNull()
    private val preferences: SharedPreferences by lazy { PreferenceManager.getDefaultSharedPreferences(context) }

    private const val KEY_LAST_POINT = "KEY_LAST_POINT"

    fun init(context: Context) {
        this.context = context
    }

    fun fetchPoints(onNext: (points: List<Point>) -> Unit) {
        FirebaseFirestore.getInstance().collection("points")
                .addSnapshotListener { snapshot, exception ->
                    snapshot?.toObjects(Point::class.java)?.run { onNext(this) }
                }
    }

    fun fetchPoint(index: Int, onNext: (point: Point) -> Unit) {
        FirebaseFirestore.getInstance().collection("points")
                .whereEqualTo("index", index)
                .limit(1)
                .addSnapshotListener { snapshot, _ -> snapshot?.toObjects(Point::class.java)?.firstOrNull()?.run { onNext(this) } }
    }

    fun fetchMessages(onNext: (points: List<Message>) -> Unit) {
        FirebaseFirestore.getInstance().collection("messages")
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .addSnapshotListener { snapshot, exception ->
                    snapshot?.toObjects(Message::class.java)?.run { onNext(this) }
                }
    }

    fun fetchLastMessage(onNext: (point: Message) -> Unit) {
        FirebaseFirestore.getInstance().collection("messages")
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .limit(1)
                .addSnapshotListener { snapshot, _ -> snapshot?.toObjects(Message::class.java)?.lastOrNull()?.run { onNext(this) } }
    }

    fun saveLastPoint(index: Int) {
        preferences.edit().putInt(KEY_LAST_POINT, index).apply()
    }

    fun getLastPoint(): Int = preferences.getInt(KEY_LAST_POINT, -1)

    fun getNextPoint(): Int = getLastPoint() + 1

    fun isFirsStart() = getLastPoint() == -1

    fun saveLastLocation(location: android.location.Location) {
        FirebaseFirestore.getInstance()
                .collection("locations")
                .document("0")
                .set(Location(GeoPoint(location.latitude, location.longitude)))
                .addOnCompleteListener { }
    }
}