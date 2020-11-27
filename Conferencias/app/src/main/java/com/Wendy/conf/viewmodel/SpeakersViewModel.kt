package com.jair.conf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jair.conf.model.Speaker
import com.jair.conf.network.Callback
import com.jair.conf.network.FirestoreService
import java.lang.Exception

class SpeakersViewModel: ViewModel() {
    val firestoreService = FirestoreService()
    val listSpeakers: MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh() {
        getSpeakersFromFirebase()
    }

    fun getSpeakersFromFirebase() {
        firestoreService.getSpeakers(object: Callback<List<Speaker>> {
            override fun onSuccess(result: List<Speaker>?) {
                listSpeakers.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        } )
    }
    fun processFinished() {
        isLoading.value = true
    }
}