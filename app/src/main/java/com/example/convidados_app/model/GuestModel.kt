package com.example.convidados_app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Guest")
class GuestModel {

    /**
     ANOTAÇÃO DE CODIGO

     - Mudamos o modelo do database e fizemos aqui
     antigo :
     // Cria a tabela chamada Guest
     db.execSQL("CREATE TABLE " +
     DataBaseConstants.GUEST.TABLE_NAME + " (" +
     DataBaseConstants.GUEST.COLUMNS.ID + " integer primary key autoincrement," +
     DataBaseConstants.GUEST.COLUMNS.NAME + " text," +
     DataBaseConstants.GUEST.COLUMNS.PRESENCE + " integer);")
     **/
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int = 0

    @ColumnInfo(name = "name")
    var name: String = ""

    @ColumnInfo(name = "presence")
    var presence: Boolean = false

}