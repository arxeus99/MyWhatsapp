package com.valentelmadafaka.mywhatsapp.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.valentelmadafaka.mywhatsapp.db.dbQuePasa.CREATE_TABLE_USER;
import static com.valentelmadafaka.mywhatsapp.db.dbQuePasa.NOMBRE_DB;
import static com.valentelmadafaka.mywhatsapp.db.dbQuePasa.TAB_MSG;
import static com.valentelmadafaka.mywhatsapp.db.dbQuePasa.TAB_USER;
import static com.valentelmadafaka.mywhatsapp.db.dbQuePasa.TAG;
import static com.valentelmadafaka.mywhatsapp.db.dbQuePasa.VERSION_DB;
import static com.valentelmadafaka.mywhatsapp.db.dbQuePasa.CREATE_TABLE_MSG;

public class HelperDB extends SQLiteOpenHelper {

    HelperDB(Context con){
        super(con, NOMBRE_DB,null,VERSION_DB);
    }

     @Override
    public void onCreate(SQLiteDatabase db){
         db.execSQL(CREATE_TABLE_USER);
         Log.i("QuePasa",CREATE_TABLE_MSG);
         db.execSQL(CREATE_TABLE_MSG);
     }

     @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
         Log.w(dbQuePasa.class.getName(),
                 "Upgrading database from version " + oldVersion + " to "
                         + newVersion + ", which will destroy all old data");
         db.execSQL("DROP TABLE IF EXISTS " + TAB_MSG);
         db.execSQL("DROP TABLE IF EXISTS " + TAB_USER);
         onCreate(db);
     }
}
