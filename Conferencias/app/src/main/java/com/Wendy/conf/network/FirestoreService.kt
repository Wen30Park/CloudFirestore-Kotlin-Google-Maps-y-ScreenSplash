package com.jair.conf.network

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.jair.conf.model.Speaker
import com.jair.conf.model.conference

const val CONFERENCES_COLLECTION_NAME = "conferences"
const val SPEAKERS_COLLECTION_NAME = "speakers"

class FirestoreService {

    val firebaseFirestore = FirebaseFirestore.getInstance()
    val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    init {
        firebaseFirestore.firestoreSettings = settings
    }

    fun getSpeakers(callback: Callback<List<Speaker>>) {
        firebaseFirestore.collection(SPEAKERS_COLLECTION_NAME)
            .orderBy("category")
            .get()
            .addOnSuccessListener { result ->
                for (doc in result) {
                    val list = result.toObjects(Speaker::class.java)
                }
            }
    }

    fun getSchedule(callback: Callback<List<conference>>) {
        firebaseFirestore.collection(CONFERENCES_COLLECTION_NAME)
            .orderBy("category")
            .get()
            .addOnSuccessListener { result ->
                for (doc in result) {
                    val list = result.toObjects(conference::class.java)
                }
            }
    }
}