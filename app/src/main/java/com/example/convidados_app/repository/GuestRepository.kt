package com.example.convidados_app.repository


class GuestRepository private constructor() {

    /**
     Singleton
     usando assim, nos podemos usar o getInstance quantas vezes quiser, que nao vai nunca ser inicializado 2x, pois se o getInstance
     estiver inicializado, ele nao vai chamar o if() e assim ele retorna "repository".

     - singleton é usado para controlar o numero de instancia de uma classe
     - se priva o construtor e usa o metodo statico chamando o getInstance controlando o acesso, a instancia.
     **/

    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(): GuestRepository {
            if (!::repository.isInitialized) {
                repository = GuestRepository()
            }
            return repository
        }

    }
}