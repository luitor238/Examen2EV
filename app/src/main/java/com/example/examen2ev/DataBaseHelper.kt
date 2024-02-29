package com.example.examen2ev

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.MediaStore.Video


class JuegosComprados(context: Context) : SQLiteOpenHelper(context, DATABASE, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE = "basedatos.db"
        private const val TABLA_VIDEOJUEGOS = "videojuegos"
        private const val KEY_ID = "id"
        private const val COLUMN_NOMBRE = "nombre"
        private const val COLUMN_VALORACION = "valoracion"
        private const val COLUMN_EMPRESA = "empresa"
        private const val COLUMN_ANIO = "anio"

    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLA_VIDEOJUEGOS ($KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_NOMBRE TEXT," +
                " $COLUMN_VALORACION DOUBLE, $COLUMN_EMPRESA TEXT, $COLUMN_ANIO INTEGER)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLA_VIDEOJUEGOS")
        onCreate(db)
    }

    fun insertarVideojuego(videojuego: Videojuego): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE, videojuego.getNombre())
            put(COLUMN_VALORACION, videojuego.getValoracion())
            put(COLUMN_EMPRESA, videojuego.getEmpresa())
            put(COLUMN_ANIO, videojuego.getAnio())

        }
        val id= db.insert(TABLA_VIDEOJUEGOS, null, values)
        db.close()
        return id
    }


    @SuppressLint("Range")
    fun getVideojuego(): ArrayList<Videojuego> {
        val lista = ArrayList<Videojuego>()
        val selectQuery = "SELECT * FROM $TABLA_VIDEOJUEGOS"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
                val valoracion = cursor.getFloat(cursor.getColumnIndex(COLUMN_VALORACION))
                val empresa = cursor.getString(cursor.getColumnIndex(COLUMN_EMPRESA))
                val anio = cursor.getInt(cursor.getColumnIndex(COLUMN_ANIO))
                val videojuego = Videojuego(nombre, valoracion)
                videojuego.setEmpresa(empresa)
                videojuego.setAnio(anio)
                lista.add(videojuego)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return lista
    }


    fun eliminarVideojuego(nombre: String): Int {
        val db = this.writableDatabase
        val resultado = db.delete(TABLA_VIDEOJUEGOS, "$COLUMN_NOMBRE=?", arrayOf(nombre))
        db.close()
        return resultado
    }

}
