package com.am.sgmobiledata.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.am.sgmobiledata.data.model.EntityYear
import com.am.sgmobiledata.data.repository.Repository
import com.am.sgmobiledata.utils.NetworkResult

class DetailsViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _yearId = MutableLiveData<Int>()
    private val _year = _yearId.switchMap { id -> repository.getData(id) }
    val year: LiveData<NetworkResult<EntityYear>> = _year

    fun start(yearId: Int) {
        _yearId.value = yearId
    }


}