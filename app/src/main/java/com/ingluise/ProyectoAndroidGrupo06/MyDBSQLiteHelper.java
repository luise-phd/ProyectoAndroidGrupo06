package com.ingluise.ProyectoAndroidGrupo06;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBSQLiteHelper extends SQLiteOpenHelper {

    public MyDBSQLiteHelper(Context context, String nombre, SQLiteDatabase.CursorFactory c, int version) {
        super(context, nombre, c, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE empleado(_id INTEGER PRIMARY KEY AUTOINCREMENT, dni TEXT, nombre TEXT, apellidos TEXT, est_civil TEXT)");
        db.execSQL("CREATE TABLE imagenes(_id INTEGER PRIMARY KEY AUTOINCREMENT, descripcion TEXT, img BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int verAnterior, int verNueva) {
        db.execSQL("DROP TABLE IF EXISTS empleado");
        db.execSQL("DROP TABLE IF EXISTS imagenes");
        db.execSQL("CREATE TABLE empleado(_id INTEGER PRIMARY KEY AUTOINCREMENT, dni TEXT, nombre TEXT, apellidos TEXT, est_civil TEXT)");
        db.execSQL("CREATE TABLE imagenes(_id INTEGER PRIMARY KEY AUTOINCREMENT, descripcion TEXT, img BLOB)");
    }
}
