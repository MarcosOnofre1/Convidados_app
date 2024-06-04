package com.example.convidados_app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.convidados_app.model.GuestModel
import com.example.convidados_app.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    /**
     Diferença do AndroidViewModel x ViewModel
       - ultilização do context, android usamos o "application" que faz o papel de context, no view usamos "context", porem os 2 tem a mesma caracteristicas.
     - ViewModel nao tem context
     **/
    private val repository = GuestRepository.getInstance(application)

    fun insert(guest: GuestModel){
        // esse .insert é do GuestRepository
        repository.insert(guest)


    }
}