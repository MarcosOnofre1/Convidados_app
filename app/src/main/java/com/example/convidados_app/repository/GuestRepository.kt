package com.example.convidados_app.repository

import android.content.Context
import android.provider.ContactsContract.Intents.Insert
import com.example.convidados_app.GuestModel


class GuestRepository private constructor(context: Context) {

    /**
     Porq da variavel guestDataBase
      - criada essa variavel para nao ocorrer o risco de instaciar o banco mais do que deveria instanciar.
     **/
    private val guestDataBase = GuestDataBase(context)

    /**
     Singleton
     usando assim, nos podemos usar o getInstance quantas vezes quiser, que nao vai nunca ser inicializado 2x, pois se o getInstance
     estiver inicializado, ele nao vai chamar o if() e assim ele retorna "repository".

     - singleton é usado para controlar o numero de instancia de uma classe
     - se priva o construtor e usa o metodo statico chamando o getInstance controlando o acesso, a instancia.
     **/
    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if (!::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun insert () {

    }

    fun update () {

    }

}