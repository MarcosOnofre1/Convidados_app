package com.example.convidados_app.repository

import android.content.ContentValues
import android.content.Context
import com.example.convidados_app.model.GuestModel
import java.lang.Exception


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

    fun insert (guest: GuestModel): Boolean {
        /**
         ANOTAÇÕES DO CODIGO
         - writableDatabase
            gravando alguma informação

         - readableDatabase
            consultando alguma informação

         - usamos o insert por ser mais seguro, para evitar erros de digitação das colunas etc. Ja no "nullColumnHack", o SQL nao permite que a inserção de uma linha,
         com todos os valores nulos. Ou seja, "nullColumnHack", voce fornece um valor para ele e ele te ajuda assim, a nao deixar que essa linha fique nula.
         Porem, no nosso caso, ele nao vai ser necessario, pois na nossa coluna, o ID é um autoincrement, entao nao deixará que fique nula.

         - ContentValues() -> Content (conteudo), ele vai carregar os conteudos para o nosso banco.

         - fizemos esse if ->  "val presence =  if (guest.presence) 1 else 0" para deixar o "presence" como inteiro, ja que ele ta como Boolean no GuestModel e no
         GuestDataBse ele recebe um inteiro, Entao foi feito esse if() para converter o Boolean para Int.

         - Fez um Try Catch para evitar Exception
         **/
        return try {
            val db = guestDataBase.writableDatabase
            val presence =  if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put("name", guest.name)
            values.put("presence", presence)

            db.insert("Guest", null, values)
            true
        } catch (e: Exception){
            false

        }

    }

    fun update () {

    }

}