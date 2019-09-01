package com.example.taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class DbSqlite extends SQLiteOpenHelper {
    public DbSqlite( @Nullable Context context) {
        super(context, "Db_sql", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String req = " CREATE TABLE taches ( id INTEGER PRIMARY KEY , nomtache VARCHAR(20), tache VRACHAR(20) , date VARCHAR(20) )";

        db.execSQL(req);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String upgrade = " DROP TABLE IF EXISTS taches ";

        db.execSQL(upgrade);
            onCreate(db);


    }

    public void Ajout(Data data)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nomtache",data.getNomtask());
        values.put("tache",data.getTask());
        values.put("date",data.getDate());

        db.insert("taches",null,values);
    }




    public ArrayList<Data> getAll()
    {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Data> array = new ArrayList<>();

        String sql=" SELECT * FROM taches";
        Cursor cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst())
        {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String nomtache = cursor.getString(cursor.getColumnIndex("nomtache"));
                String tache = cursor.getString(cursor.getColumnIndex("tache"));
                String date = cursor.getString(cursor.getColumnIndex("date"));

                Data data= new Data(id,nomtache,tache,date);
                array.add(data);
            }while (cursor.moveToNext());
        }


        return array;
    }


    public void Update(Data data)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nomtache",data.getNomtask());
        values.put("tache",data.getTask());
        values.put("date",data.getDate());
        db.update("taches",values,"id=?",new String[]{String.valueOf(data.getId())});
    }

    public void Delete(int id)
    {
        SQLiteDatabase db = getWritableDatabase();

        db.delete("taches","id=?",new String[]{String.valueOf(id)});
    }



}
