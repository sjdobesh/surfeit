package com.tfsg.surfeit

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// database manager to act as interface to an sqlite database
// To access this in fragments use 'DatabaseManager db = DatabaseManager(this)'
class DatabaseManager(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        // create the main table of entries
        var sqlTable = "create table $TABLE_FRIDGE ( "
        sqlTable += "$ID integer primary key autoincrement, " // set primary key
        sqlTable += "${TITLE}title, "
        sqlTable += "${PURCHASE_DATE}purchase date, "
        sqlTable += "${EXPIRATION_DATE}expiration date, "
        sqlTable += "${COUNT}count )"
        db.execSQL(sqlTable)    }

    // you just have to override this, we don't need to use it
    override fun onUpgrade(db: SQLiteDatabase, oldV: Int, newV: Int) {}

    // insert a food class as an entry into the database
    fun insert(new_entry: Food) {
        val db = this.writableDatabase
        var sqlInsert = "insert into $TABLE_FRIDGE "
        sqlInsert += "values( null, '"
        sqlInsert += "${new_entry.title}', '"
        sqlInsert += "${new_entry.purchaseDate}', '"
        sqlInsert += "${new_entry.expirationDate}', '"
        sqlInsert += "${new_entry.count}')"
        db.execSQL(sqlInsert)
        db.close()
    }

    // assumes the item exists in the database, therefore, removes item from database
    fun remove(entry: String) {
        val db = this.writableDatabase
        val sqlRemove = "DELETE FROM $TABLE_FRIDGE WHERE titletitle = '" + entry + "' COLLATE NOCASE"

        db.execSQL(sqlRemove)
    }

    // checks if item exists in teh database: true that it exists, else, false
    fun exist(entry: String): Boolean {
        var exist = false
        val sqlExist = "SELECT * FROM $TABLE_FRIDGE WHERE titletitle = '" + entry + "' COLLATE NOCASE"
        val db = this.writableDatabase
        val cursor = db.rawQuery(sqlExist, null)

        if (cursor.getCount() > 0) {
            exist = true
        }

        cursor.close()
        db.close()
        return exist
    }

    @SuppressLint("Recycle")
    fun selectAll(): ArrayList<Food>  {
        val sqlQuery = "select * from $TABLE_FRIDGE"
        val db = this.writableDatabase
        val cursor = db.rawQuery( sqlQuery, null )

        val entries = ArrayList<Food>( )
        while(cursor.moveToNext()) {
            val currentItem = Food(cursor.getString( 1 ), cursor.getString( 2 ),cursor.getString( 3 ), cursor.getInt(4))
            currentItem.id = cursor.getInt(0)
            entries.add(currentItem)
        }
        db.close( )
        return entries
    }

    fun returnfoodtitles(): ArrayList<String>  {
        val sqlQuery = "select * from $TABLE_FRIDGE"
        val db = this.writableDatabase
        val cursor = db.rawQuery( sqlQuery, null )

        val food = ArrayList<String>( )
        while(cursor.moveToNext()) {
            val currentItem = Food(cursor.getString( 1 ), cursor.getString( 2 ),cursor.getString( 3 ), cursor.getInt(4))
            food.add(currentItem.title)
        }
        db.close( )
        return food
    }
    fun returnfoodcount(): ArrayList<String>  {
        val sqlQuery = "select * from $TABLE_FRIDGE"
        val db = this.writableDatabase
        val cursor = db.rawQuery( sqlQuery, null )

        val count = ArrayList<String>( )
        while(cursor.moveToNext()) {
            val currentItem = Food(cursor.getString( 1 ), cursor.getString( 2 ),cursor.getString( 3 ), cursor.getInt(4))
            val entry = " "+currentItem.count.toString()
            count.add(entry)
        }
        db.close( )
        return count
    }

    fun returnfooddate(): ArrayList<String>  {
        val sqlQuery = "select * from $TABLE_FRIDGE"
        val db = this.writableDatabase
        val cursor = db.rawQuery( sqlQuery, null )

        val dates = ArrayList<String>( )
        while(cursor.moveToNext()) {
            val currentItem = Food(cursor.getString( 1 ), cursor.getString( 2 ),cursor.getString( 3 ), cursor.getInt(4))
            dates.add(currentItem.expirationDate)
        }
        db.close( )
        return dates
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