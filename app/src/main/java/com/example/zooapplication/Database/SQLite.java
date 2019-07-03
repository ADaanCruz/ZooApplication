package com.example.zooapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.util.Log;

import java.util.ArrayList;

public class SQLite {
    private Sql sql;
    private SQLiteDatabase db;

    public SQLite(Context context) {
        sql = new Sql(context);
    }

    public SQLite() {
    }

    public void abrir() {
        db = sql.getReadableDatabase();
        Log.i("SQLite", "Se abre conexión a la base de datos" + sql.getDatabaseName());
    }

    public void cerrar() {
        db.close();
        Log.i("SQLite", "Se cierra conexión a la base de datos" + sql.getDatabaseName());
    }

    public boolean addRegistroAnimal(int id, String clas, String esp, String nom, String sex,
                                     String date, String habit, String food) {
        ContentValues content = new ContentValues();
        content.put("id", id);
        content.put("clasificacion", clas);
        content.put("especie", esp);
        content.put("nombre", nom);
        content.put("sexo", sex);
        content.put("fecha_ing", date);
        content.put("habitat", habit);
        content.put("alimento", food);

        return (db.insert("animals", null, content) != (-1));
    }

    public Cursor getRegistroAnimal() {
        return db.rawQuery("SELECT * FROM animals", null);
    }

    public ArrayList<String> getAnimal(Cursor cursor) {
        ArrayList<String> list = new ArrayList<>();
        String item;
        if (cursor.moveToFirst()) {
            do {
                item = "";
                item += "ID: " + cursor.getInt(0) + "\r\n";
                item += "Clasificación: " + cursor.getInt(1) + "\r\n";
                item += "Especie: " + cursor.getInt(2) + "\r\n";
                item += "Nombre: " + cursor.getInt(3) + "\r\n";
                item += "Sexo: " + cursor.getInt(4) + "\r\n";
                item += "Fecha ingreso: " + cursor.getInt(5) + "\r\n";
                item += "Hábitat: " + cursor.getInt(6) + "\r\n";
                item += "Alimento: " + cursor.getInt(7) + "\r\n";

                list.add(item);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public ArrayList<String> getID(Cursor cursor) {
        ArrayList<String> list = new ArrayList<>();
        String item = "";
        if (cursor.moveToFirst()) {
            do {
                item += "ID: [" + cursor.getInt(0) + "]\r\n";
                list.add(item);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public String updateRegAnimal(int id, String clas, String esp, String nom, String sex,
                                  String date, String habit, String food) {
        ContentValues content = new ContentValues();
        content.put("id", id);
        content.put("clasificacion", clas);
        content.put("especie", esp);
        content.put("nombre", nom);
        content.put("sexo", sex);
        content.put("fecha_ing", date);
        content.put("habitat", habit);
        content.put("alimento", food);
        int cant = db.update("animals", content, "id="+id, null);
        if (cant == 1) {
            return "Animal modificado";
        } else {
            return "Error: El usuario no se modificó";
        }
    }

    public Cursor getCant(int id) {
        return db.rawQuery("SELECT * FROM animals WHERE id=" + id, null);
    }

    public int Eliminar (Editable id) {
        return db.delete("animals", "id="+id, null);
    }
}
