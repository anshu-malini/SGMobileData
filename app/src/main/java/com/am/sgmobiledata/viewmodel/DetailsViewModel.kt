package com.am.sgmobiledata.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.am.sgmobiledata.data.repository.Repository
import kotlinx.coroutines.runBlocking

class DetailsViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {
    private var _yearId: Int = 0

    fun getYearData() =
        runBlocking {
            return@runBlocking repository.getData(_yearId)
        }

    fun start(yearId: Int) {
        _yearId = yearId
    }

}