package com.example.convidados_app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidados_app.model.GuestModel
import com.example.convidados_app.repository.GuestRepository

class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {

    /**
     ANOTAÇÃO DE CODIGO

     - para evitar caso tenhamos que ter mais fun e ter que instanciar toda vez nas fun o .getInstance(), fizemos o repository intanciando ele, assim,
     podemos chamar o repository ja com a instancia do getInstance.

     - e o getInstance chama o context, colocando o application nele do AndroidViewModel

     - tiramos o tipo do repository, porq conseguimos inferir o GuetRepository. (antes estava como latenit var)

     - atribuimos o listAllGuests.value para o repository pegar a list
     **/
    private val repository = GuestRepository.getInstance(application.applicationContext)

    private val listAllGuests = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = listAllGuests

    fun getAll() {
        listAllGuests.value = repository.getAll()
    }
}