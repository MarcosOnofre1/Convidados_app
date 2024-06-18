package com.example.convidados_app.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.convidados_app.constants.DataBaseConstants


class GuestDataBase(context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object {
        private const val NAME = "guestdb"
        private const val VERSION = 1
    }

    /**
     CREATE TABLE Guest
     - primary key = quando um valor, receba uma chave primaria, aquele valor que recebe, ele é unico. Ou seja, nao tem um ID igual.
     - autoincrement = O banco é responsavel para criar os ID's unicos.
     **/
    override fun onCreate(db: SQLiteDatabase) {
        // Cria a tabela chamada Guest
        db.execSQL("CREATE TABLE " +
                DataBaseConstants.GUEST.TABLE_NAME + " (" +
                DataBaseConstants.GUEST.COLUMNS.ID + " integer primary key autoincrement," +
                DataBaseConstants.GUEST.COLUMNS.NAME + " text," +
                DataBaseConstants.GUEST.COLUMNS.PRESENCE + " integer);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }


}