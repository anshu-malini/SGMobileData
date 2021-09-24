package com.am.sgmobiledata.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.am.sgmobiledata.data.repository.Repository

class MainViewModel @ViewModelInject constructor(private val repository: Repository) : ViewModel() {
    val response = repository.getAllData()

}
