package com.tfsg.surfeit

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

abstract class DatabaseManager(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        var sqlTable = "create table $TABLE_FRIDGE ( "
        sqlTable += "$ID integer primary key autoincrement, "
        sqlTable += "${TITLE}title, "
        sqlTable += "${PURCHASE_DATE}purchase date, "
        sqlTable += "${EXPIRATION_DATE}expiration date, "
        sqlTable += "${COUNT}count )"
        db.execSQL(sqlTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldV: Int, newV: Int) {}
    fun insert(new_entry: Food) {
        val db = this.writableDatabase
        var sqlInsert = "insert into $TABLE_FRIDGE "
        sqlInsert += "values( null, '"
        sqlInsert += new_entry.title + "', '"
        sqlInsert += new_entry.purchase_date + "', '"
        sqlInsert += new_entry.expiration_date + "', '"
        sqlInsert += new_entry.count.toString() + "')"
        db.execSQL(sqlInsert)
        db.close()
    }

    companion object {
        // database details
        private const val DATABASE_NAME = "fridge"
        private const val DATABASE_VERSION = 1

        // define table and entries
        private const val TABLE_FRIDGE = "entry"
        private const val ID = "id"
        private const val TITLE = "title"
        private const val PURCHASE_DATE = "purchase_date"
        private const val EXPIRATION_DATE = "expiration_date"
        private const val COUNT = "count"
    }
}