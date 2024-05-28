package com.example.convidados_app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.convidados_app.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    /**
     Diferença do AndroidViewModel x ViewModel
       - ultilização do context, android usamos o "application", no view usamos "context", porem os 2 tem a mesma caracteristicas.
     **/
    private val repository = GuestRepository.getInstance(application)

    fun abc(){

    }
}