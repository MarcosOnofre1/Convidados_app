package com.example.convidados_app.repository

import android.content.ContentValues
import android.content.Context
import android.provider.ContactsContract.Data
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
    usando assim, nos podemos usar o fun getInstance quantas vezes quiser, que nao vai nunca ser inicializado 2x, pois se o getInstance
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

        - usamos o insert por ser mais seguro, para evitar erros de digitação das colunas e etc. Ja no "nullColumnHack", o SQL nao permite que a inserção de uma linha,
        com todos os valores nulos. Ou seja, "nullColumnHack", voce fornece um valor para ele e ele te ajuda assim, a nao deixar que essa linha fique nula.
        Porem, no nosso caso, ele nao vai ser necessario, pois na nossa coluna, o ID é um autoincrement, entao nao deixará que fique nula.

        - ContentValues() -> Content (conteudo), ele vai carregar os conteudos para o nosso banco.

        - fizemos esse if ->  "val presence =  if (guest.presence) 1 else 0" para deixar o "presence" como inteiro, ja que ele ta como Boolean no GuestModel e no
        GuestDataBase ele recebe um inteiro, Entao foi feito esse if() para converter o Boolean para Int.

        - Fez um Try Catch para evitar Exception
         **/
        return try {
            val db = guestDataBase.writableDatabase
            val presence = if (guest.presence) 1 else 0

            val tableName = DataBaseConstants.GUEST.TABLE_NAME
            val columnsName = DataBaseConstants.GUEST.COLUMNS.NAME
            val columnsPresence = DataBaseConstants.GUEST.COLUMNS.PRESENCE

            val values = ContentValues()
            values.put(columnsName, guest.name)
            values.put(columnsPresence, presence)

            db.insert(tableName, null, values)
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

            val tableName = DataBaseConstants.GUEST.TABLE_NAME
            val columnsId = DataBaseConstants.GUEST.COLUMNS.ID
            val columnsName = DataBaseConstants.GUEST.COLUMNS.NAME
            val columnsPresence = DataBaseConstants.GUEST.COLUMNS.PRESENCE

            val values = ContentValues()
            values.put(columnsName, guest.name)
            values.put(columnsPresence, presence)

            val selection = "$columnsId = ?"
            val args = arrayOf(guest.id.toString())

            db.update(tableName, values, selection, args)
            true

        } catch (e: Exception) {
            false

        }
    }

    fun delete(id: Int): Boolean {
        /**
        ANOTAÇÕES DE CODIGO

        - Aqui, usamos so o ID na .delete() porq o ID é unico, entao somente com ele conseguimos ja conseguimos deletar quem nos queremos.

        - no arrayOf(), usamos somente o ID, que com ele ja é o necessario para o args.
         **/
        return try {
            val db = guestDataBase.writableDatabase

            val tableName = DataBaseConstants.GUEST.TABLE_NAME
            val columnsId = DataBaseConstants.GUEST.COLUMNS.ID

            val selection = "$columnsId = ?"
            val args = arrayOf(id.toString())

            db.delete(tableName, selection, args)
            true

        } catch (e: Exception) {
            false

        }
    }

    fun getAll(): List<GuestModel> {
        /**
        ANOTAÇÕES DO CODIGO

        - nesse getAll, fizemos um readable porq vamos consultar os dados.

        - no arrayOf() a ordem que foi posta, temos que obter os dados por essa ordem, nao importa a ordem que voce ponha, com tanto que voce siga a ordem, tudo bem.

        - entendo melhor um pouco do cursor:
        O cursor, podemos pensar nele como uma "seta", esse cursor que vai te possibilitar de obter os dados.
        Ele sempre apontará pelo começo da tabela, ele interage, linha por linha, pegando os dados e transformando no objeto que desejamos.
        O cursor consegue tambem, avisar quando chegou no final da tabela.
        Tambem consegue avisar, quais e quantas colunas da tabela temos, e tambem, podemos pegar separadamente ou quantas colunas nos quisermos da tabela.

        - No while, a variavel id, o getInt() ele quer saber, numa tabela, (array), qual a index que voce quer mexer. Vamos supor que:

        0,   1,    2
        id, name, presence

        entao a index do id é 0.

        para boas praticas, voce pode usar o "0" no getInt(), porem, nao é o melhor a se fazer, pois pode haver que voce mude a ordem das colunas (projection), e
        que a index "0", nao seja index desejada mais, e sem duvidas, pode dar uma Execption.

        - O erro que da na variavel id do while, é erro do android studio.

        - O cursor é como se fosse um arquivo txt. Nos abrimos, escrevemos o conteudo nele e depois fechamos. Entao, apos o conteudo, colocamos o "cursor.closed()".
         **/
        val list = mutableListOf<GuestModel>()

        try {
            val db = guestDataBase.readableDatabase

            val tableName = DataBaseConstants.GUEST.TABLE_NAME
            val columnsId = DataBaseConstants.GUEST.COLUMNS.ID
            val columnsName = DataBaseConstants.GUEST.COLUMNS.NAME
            val columnsPresence = DataBaseConstants.GUEST.COLUMNS.PRESENCE


            val projection = arrayOf(
                columnsId,
                columnsName,
                columnsPresence,
            )

            val cursor =
                db.query(tableName, projection, null, null, null, null, null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    val id =
                        cursor.getInt(cursor.getColumnIndex(columnsId))
                    val name =
                        cursor.getString(cursor.getColumnIndex(columnsName))
                    val presence =
                        cursor.getInt(cursor.getColumnIndex(columnsPresence))

                    list.add(GuestModel(id, name, presence == 1))

                }
            }
            cursor.close()

        } catch (e: Exception) {
            return list

        }
        return list
    }

    fun getPresent(): List<GuestModel> {
        /**
        ANOTAÇÕES DO CODIGO

         - rawQuery, é uma query crua, ele pode ser executado em SQL em formato String. Ou seja, podemos executar comandos no nosso banco, que no nosso caso,
         vamos fazer um comando de consulta.

        **/
        val list = mutableListOf<GuestModel>()

        try {
            val db = guestDataBase.readableDatabase

            val columnsId = DataBaseConstants.GUEST.COLUMNS.ID
            val columnsName = DataBaseConstants.GUEST.COLUMNS.NAME
            val columnsPresence = DataBaseConstants.GUEST.COLUMNS.PRESENCE

            val cursor = db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 1", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    val id = cursor.getInt(cursor.getColumnIndex(columnsId))
                    val name = cursor.getString(cursor.getColumnIndex(columnsName))
                    val presence = cursor.getInt(cursor.getColumnIndex(columnsPresence))

                    list.add(GuestModel(id, name, presence == 1))

                }
            }
            cursor.close()

        } catch (e: Exception) {
            return list

        }
        return list
    }

    fun getAbsent(): List<GuestModel> {

        val list = mutableListOf<GuestModel>()

        try {
            val db = guestDataBase.readableDatabase

            val columnsId = DataBaseConstants.GUEST.COLUMNS.ID
            val columnsName = DataBaseConstants.GUEST.COLUMNS.NAME
            val columnsPresence = DataBaseConstants.GUEST.COLUMNS.PRESENCE

            val cursor = db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 0", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    val id = cursor.getInt(cursor.getColumnIndex(columnsId))
                    val name = cursor.getString(cursor.getColumnIndex(columnsName))
                    val presence = cursor.getInt(cursor.getColumnIndex(columnsPresence))

                    list.add(GuestModel(id, name, presence == 1))

                }
            }
            cursor.close()

        } catch (e: Exception) {
            return list

        }
        return list
    }

}