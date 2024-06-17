package com.example.convidados_app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidados_app.model.GuestModel
import com.example.convidados_app.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    /**
     Diferença do AndroidViewModel x ViewModel
       - ultilização do context, android usamos o "application" que faz o papel de context, no view usamos "context", porem os 2 tem a mesma caracteristicas.
     - ViewModel nao tem context

     - para buscar os dados quando clicka no convidado, usamos o guestModel.values = repository.get(id)

     - chamando o save pra tanto pra att quanto pra savar
     **/
    private val repository = GuestRepository.getInstance(application)

    private val guestModel = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = guestModel

    fun insert(guest: GuestModel){
        // esse .insert é do GuestRepository
        repository.insert(guest)

    }

    fun save(guest: GuestModel){
        if (guest.id == 0){
            repository.insert(guest)
        } else {
            repository.update(guest)
        }

    }

    fun get(id: Int) {
        guestModel.value = repository.get(id)
    }
}