package com.example.rmsi.sqlliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Amitabh.Basu on 17-07-2015.
 */
public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1; //note: update the version no whenever there is change in database structure.
    private static final String DATABASE_NAME = "productsDB";
    private static final String TABLE_PRDODUCTS = "products";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PRODUCTNAME = "productname";
    private static final String TAG = "SQLite";

   /* public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }*/

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        /*This gets executed only for the very first time when database is created*/
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_PRDODUCTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODUCTNAME + " TEXT " +
                ");";

        try {
            db.execSQL(query);
        }catch(SQLException sqle){
            Log.e(TAG, sqle.getMessage(), sqle);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRDODUCTS);
        onCreate(db);
    }

    public void addProduct(Product product){
        ContentValues contentValues = new ContentValues();
        Log.d(TAG,product.get_productName());
        contentValues.put(COLUMN_PRODUCTNAME, product.get_productName());

        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert(TABLE_PRDODUCTS, null, contentValues);
        Log.d(TAG, String.valueOf(id));
        db.close();
    }

    public void deleteProduct(String productName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRDODUCTS + " WHERE " + COLUMN_PRODUCTNAME + "=\"" + productName +"\";" );
        db.close();
    }

    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRDODUCTS + " WHERE 1";

        Log.d(TAG, "About to create cursor");
        Cursor cursor = db.rawQuery(query, null);
        if(cursor != null && cursor.getCount()>0){
            Log.d(TAG, "Has records to show");

            if(cursor.moveToFirst()){
                do{
                    if(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCTNAME)) != null){
                        dbString += cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCTNAME));
                        dbString += "\n";
                    }
                }while(cursor.moveToNext());
            }
        }
        db.close();
        Log.d(TAG, dbString);
        return dbString;
    }
}