package com.jair.conf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jair.conf.model.conference
import com.jair.conf.network.Callback
import com.jair.conf.network.FirestoreService
import java.lang.Exception

class ScheduleViewModel: ViewModel() {
    val firestoreService = FirestoreService()
    val listSchedule: MutableLiveData<List<conference>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh() {
        getScheduleFromFirebase()
    }

    fun getScheduleFromFirebase(){
        firestoreService.getSchedule(object: Callback<List<conference>> {
            override fun onSuccess(result: List<conference>?) {
                listSchedule.postValue(result)
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