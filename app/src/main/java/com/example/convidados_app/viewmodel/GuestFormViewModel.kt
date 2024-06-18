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

     *Criação do saveGuest
      - o saveguest agora recebe o reotirno de insert e  de update.
     -  agora pode ser capaz de ser observado na nossa fun observe() do GuestFormAcitivty
     **/
    private val repository = GuestRepository.getInstance(application)

    private val guestModel = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = guestModel

    private val _saveGuest = MutableLiveData<String>()
    val saveGuest: LiveData<String> = _saveGuest

    fun save(guest: GuestModel){
        if (guest.id == 0){
            if(repository.insert(guest)){
                _saveGuest.value = "Inserção feita com sucesso!"
            } else {
                _saveGuest.value = "Falha"

            }
        } else {
            if(repository.update(guest)){
                _saveGuest.value = "Atualização feita com sucesso!"
            } else {
                _saveGuest.value = "Falha"

            }
        }

    }

    fun get(id: Int) {
        guestModel.value = repository.get(id)
    }
}