package com.example.zooapplication.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Sql extends SQLiteOpenHelper {

    private static final String database = "zoo";
    private static final int VERSION = 1;
    private final String table_animals =
            "CREATE TABLE animals (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "clasificacion TEXT NOT NULL," +
                    "especie TEXT NOT NULL," +
                    "nombre TEXT NOT NULL," +
                    "sexo TEXT NOT NULL," +
                    "fecha_ing TEXT NOT NULL," +
                    "habitat TEXT NOT NULL," +
                    "alimento TEXT NOT NULL" +
            ")";

    public Sql(@Nullable Context context) {
        super(context, database, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(table_animals);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion>oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS animals");
            db.execSQL(table_animals);
        }
    }
}
