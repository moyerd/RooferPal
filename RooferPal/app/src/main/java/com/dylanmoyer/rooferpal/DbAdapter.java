package com.dylanmoyer.rooferpal;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbAdapter {

    // Define static variables
    public static int dbversion = 6;
    public static String dbname = "HomesDB";
    public static String dbTable = "homes";

    private static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context,dbname,null, dbversion);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS "+dbTable+" (_id INTEGER PRIMARY KEY autoincrement,name, number, email, address, city, state, zip, roofsize, roofpitch, rooftype, costest, comments, UNIQUE(number))");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+dbTable);
            onCreate(db);
        }
    }

    // Establish a connection with SQLiteDataBase
    private final Context c;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqlDb;

    public DbAdapter(Context context) {
        this.c = context;
    }
    public DbAdapter open() throws SQLException {
        dbHelper = new DatabaseHelper(c);
        sqlDb = dbHelper.getWritableDatabase();
        return this;
    }

    // Insert data
    public void insert(String text2,String text3,String text4,String text5,String text6,String text7,String text8,String text9,String text10,String text11,String text12) {
        if(!isExist(text3)) {
            sqlDb.execSQL("INSERT INTO homes (name,number,email,address,city,state,zip,roofsize,roofpitch,costest,comments) VALUES('" + text2 + "','" + text3 + "','" + text4 + "','" + text5 + "','" +text6+ "','" +text7+ "','" +text8+"','" +text9+ "','" +text10+ "','" +text11+ "','" +text12+ "')");
        }
    }
    // Checks entry already in database or not
    public boolean isExist(String num){
        String query = "SELECT number FROM homes WHERE number='"+num+"' LIMIT 1";
        Cursor row = sqlDb.rawQuery(query, null);
        return row.moveToFirst();
    }
    // Edit data
    public void update(int id,String text2,String text3,String text4,String text5,String text6,String text7,String text8,String text9,String text10,String text11,String text12) {
        sqlDb.execSQL("UPDATE "+dbTable+" SET name='"+text2+"', number='"+text3+"', email='"+text4+"', address='"+text5+"', city='"+text6+"', state='"+text7+"', zip='"+text8+"', roofsize='"+text9+"', roofpitch='"+text10+"', costest='"+text11+"', comments='"+text12+"'   WHERE _id=" + id);
    }


    // Delete data
    public void delete(int id) {
        sqlDb.execSQL("DELETE FROM "+dbTable+" WHERE _id="+id);
    }

    // Fetch data
    public Cursor fetchAllData() {
        String query = "SELECT * FROM "+dbTable;
        Cursor row = sqlDb.rawQuery(query, null);
        if (row != null) {
            row.moveToFirst();
        }
        return row;
    }

    // Fetch data by filter
    public Cursor fetchdatabyfilter(String inputText,String filtercolumn) throws SQLException {
        Cursor row = null;
        String query = "SELECT * FROM "+dbTable;
        if (inputText == null  ||  inputText.length () == 0)  {
            row = sqlDb.rawQuery(query, null);
        }else {
            query = "SELECT * FROM "+dbTable+" WHERE "+filtercolumn+" like '%"+inputText+"%'";
            row = sqlDb.rawQuery(query, null);
        }
        if (row != null) {
            row.moveToFirst();
        }
        return row;
    }
}
