package com.example.convidados_app.repository

import android.content.ContentValues
import android.content.Context
import com.example.convidados_app.constants.DataBaseConstants
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

    fun insert(guest: GuestModel): Boolean {
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
            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)

            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, values)
            true
        } catch (e: Exception) {
            false

        }

    }

    fun update(guest: GuestModel): Boolean {
        /**
        ANOTAÇÕES DE CODIGO

        - O .update vai ser diferente do .insert, aqui ele vai querer um WhereClause, que é uma String e tbm uma Array do tipo String. Nosso "WhereClause"
        é chamado de "selection" e nossa Array de "args".

        - a selection vai funcionar da seguinte maneira, a coluna que no caso é o ID é colocado um igual a um valor (id = ?) o "?", ela vai ser interpolada
        com os valores do "args".

        - usamos o .toString() no arrayOf, porq ele estava encontrando um inteiro, que no caso, queremos uma string.
         **/
        return try {
            val db = guestDataBase.writableDatabase
            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(guest.id.toString())

            db.update(DataBaseConstants.GUEST.TABLE_NAME, values, selection, args)

            true
        } catch (e: Exception) {
            false

        }
    }

    fun delete(id: Int): Boolean {
        /**
         ANOTAÇÕES DE CODIGO

         - Aqui, usamos so o ID na fun delete() porq o ID é unico, entao somente com ele conseguimos ja conseguimos deletar quem nos queremos.

         - no arrayOf(), usamos somente o ID, que com ele ja é o necessario para o args.
         **/
        return try {
            val db = guestDataBase.writableDatabase

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)

            true
        } catch (e: Exception) {
            false

        }

    }

}