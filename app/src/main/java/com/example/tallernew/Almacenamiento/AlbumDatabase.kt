package com.example.tallernew.Almacenamiento

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AlbumDatabase(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "AlbumDatabase.db"
        const val TABLE_NAME = "albums"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_ARTIST = "artist"
        const val COLUMN_RELEASE_DATE = "releaseDate"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_TITLE TEXT," +
                "$COLUMN_ARTIST TEXT," +
                "$COLUMN_RELEASE_DATE TEXT)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertAlbum(album: Album): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, album.title)
            put(COLUMN_ARTIST, album.artist)
            put(COLUMN_RELEASE_DATE, album.releaseDate)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    fun getAllAlbums(): List<Album> {
        val db = this.readableDatabase
        val cursor: Cursor = db.query(
            TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            "$COLUMN_ID DESC"
        )
        val albums = mutableListOf<Album>()
        with(cursor) {
            while (moveToNext()) {
                val album = Album(
                    id = getInt(getColumnIndexOrThrow(COLUMN_ID)),
                    title = getString(getColumnIndexOrThrow(COLUMN_TITLE)),
                    artist = getString(getColumnIndexOrThrow(COLUMN_ARTIST)),
                    releaseDate = getString(getColumnIndexOrThrow(COLUMN_RELEASE_DATE))
                )
                albums.add(album)
            }
        }
        cursor.close()
        return albums
    }

    fun updateAlbum(id: Int, newDescription: String) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_RELEASE_DATE, newDescription)
        }
        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(id.toString())
        db.update(TABLE_NAME, values, selection, selectionArgs)
    }

    fun deleteAlbum(title: String) {
        val db = this.writableDatabase
        val selection = "$COLUMN_TITLE = ?"
        val selectionArgs = arrayOf(title)
        db.delete(TABLE_NAME, selection, selectionArgs)
    }
}