package com.valentelmadafaka.mywhatsapp.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class dbQuePasa{

    public static final int VERSION_DB = 3;
    public static final String TAG = "DBInterface";
    public static final String NOMBRE_DB = "quepasa.db";
    public static final String TAB_USER = "usuario";
    public static final String COL_USER_ID = "id";
    public static final String COL_USER_NAME = "name";
    public static final String COL_USER_EMAIL = "email";
    public static final String COL_USER_IMG = "img";
    public static final String CREATE_TABLE_USER = "create table " + TAB_USER +
            "("
            + COL_USER_ID + " integer primary key, "
            + COL_USER_NAME + " text not null, "
            + COL_USER_EMAIL + " text, "
            + COL_USER_IMG + " text)";
    public static final String TAB_MSG = "msg";
    public static final String COL_MSG_ID = "id";
    public static final String COL_MSG_MSG = "msg";
    public static final String COL_MSG_DATE = "date";
    public static final String COL_MSG_ID_USER = "userId";
    public static final String COL_MSG_LAST_MSG = "lstMsg";
    public static final String CREATE_TABLE_MSG = "create table " + TAB_MSG + "("
            + COL_MSG_ID + " integer primary key, "
            + COL_MSG_MSG + " text not null, "
            + COL_MSG_DATE + " text not null, "
            + COL_MSG_ID_USER + " integer not null, "
            + COL_MSG_LAST_MSG + " integer default 0 not null, "
            + "FOREIGN KEY ("+ COL_MSG_ID_USER +") REFERENCES " + TAB_USER +
            "(" + COL_USER_ID + "))";


    private Context context;
    private HelperDB helperDB;
    private SQLiteDatabase bd;

    public dbQuePasa(Context context){
        this.context = context;
        helperDB = new HelperDB(context);
    }

    public dbQuePasa obre() throws SQLException{
        bd = helperDB.getWritableDatabase();
        return this;
    }

    public void tanca(){ helperDB.close(); }

    public SQLiteDatabase getBd(){ return this.bd; }

    public long insertaMensaje(String msg_id, String msg, String date, String userId){
        ContentValues initailValues = new ContentValues();
        initailValues.put(COL_MSG_ID, msg_id);
        initailValues.put(COL_MSG_MSG, msg);
        initailValues.put(COL_MSG_DATE, date);
        initailValues.put(COL_MSG_ID_USER, userId);
        return bd.insert(TAB_MSG, null, initailValues);
    }

    public Cursor obtenMensajes(){
        return bd.query(TAB_MSG, new String[]{COL_MSG_ID, COL_MSG_MSG,COL_MSG_DATE,COL_MSG_ID_USER}, null, null, null, null, null);
    }
}
