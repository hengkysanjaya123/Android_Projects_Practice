package com.example.myapplication.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.myapplication.Model.Cat

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_FAVOURITES =
            "CREATE TABLE $FAVOURITES_CAT_TABLE_NAME ($FAVOURITES_CAT_ID_COLUMN TEXT PRIMARY KEY,$FAVOURITES_CAT_URL_COLUMN TEXT)"
        db?.execSQL(CREATE_TABLE_FAVOURITES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE_FAVOURITES = "DROP TABLE IF EXISTS $FAVOURITES_CAT_TABLE_NAME"
        db?.execSQL(DROP_TABLE_FAVOURITES)

        onCreate(db)
    }

    fun addFavourites(cat: Cat): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(FAVOURITES_CAT_ID_COLUMN, cat.id)
        values.put(FAVOURITES_CAT_URL_COLUMN, cat.url)
        val _success = db.insert(FAVOURITES_CAT_TABLE_NAME, null, values)
        db.close()
        return Integer.parseInt(_success.toString()) != -1
    }

    fun getFavourites(): List<Cat> {

        val list = arrayListOf<Cat>()

        val db = this.readableDatabase
        val select = "SELECT * FROM $FAVOURITES_CAT_TABLE_NAME"
        val cursor = db.rawQuery(select, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getString(cursor.getColumnIndex("$FAVOURITES_CAT_ID_COLUMN"))
                    val url = cursor.getString(cursor.getColumnIndex("$FAVOURITES_CAT_URL_COLUMN"))

                    list.add(Cat(id, url))

                }
                while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()
        return list
    }

    fun deleteCat(cat : Cat): Boolean{

        val db = this.writableDatabase
        val _success = db.delete(FAVOURITES_CAT_TABLE_NAME, "$FAVOURITES_CAT_ID_COLUMN = ?", arrayOf(cat.id))
        db.close()

        return Integer.parseInt(_success.toString()) != -1
    }

    companion object {
        private val DB_NAME = "db_cat"
        private val DB_VERSION = 1

        private val FAVOURITES_CAT_TABLE_NAME = "FAVOURITES_CAT_TABLE"
        private val FAVOURITES_CAT_ID_COLUMN = "id"
        private val FAVOURITES_CAT_URL_COLUMN = "url"
    }
}