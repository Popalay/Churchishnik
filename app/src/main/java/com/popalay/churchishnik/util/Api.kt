package com.popalay.churchishnik.util

import com.google.firebase.firestore.FirebaseFirestore
import com.popalay.churchishnik.model.Message
import com.popalay.churchishnik.model.Point

object Api {

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
                .addSnapshotListener { snapshot, exception ->
                    snapshot?.toObjects(Message::class.java)?.run { onNext(this) }
                }
    }

    fun fetchLastMesssage(onNext: (point: Message) -> Unit) {
        FirebaseFirestore.getInstance().collection("messages")
                .limit(1)
                .addSnapshotListener { snapshot, _ -> snapshot?.toObjects(Message::class.java)?.lastOrNull()?.run { onNext(this) } }
    }
}