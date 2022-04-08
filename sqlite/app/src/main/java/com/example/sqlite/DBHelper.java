package com.example.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static  final String DB_NAME ="Credential.db";
    public static final String TABLE_NAME ="TBL_CREDENTIAL";
    public static final int DB_VERSION = 1;
    public DBHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE "+TABLE_NAME +"("+
                TBL_CREDENTIAL.id +" INTEGER PRIMARY KEY,"+
                TBL_CREDENTIAL.access_token +" TEXT,"+
                TBL_CREDENTIAL.refresh_token +" TEXT,"+
                TBL_CREDENTIAL.account_id +" TEXT,"+
                TBL_CREDENTIAL.expire_time +" TEXT,"+
                TBL_CREDENTIAL.created_at +" TEXT,"+
                TBL_CREDENTIAL.updated_at +" TEXT,"+
                TBL_CREDENTIAL.status +" INTEGER )";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public String insertDB(Credential credential){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TBL_CREDENTIAL.access_token,credential.getAccess_token());
        cv.put(TBL_CREDENTIAL.refresh_token,credential.getRefresh_token());
        cv.put(TBL_CREDENTIAL.account_id,credential.getAccount_id());
        cv.put(TBL_CREDENTIAL.expire_time,credential.getExpire_time());
        cv.put(TBL_CREDENTIAL.created_at,credential.getCreated_at());
        cv.put(TBL_CREDENTIAL.updated_at,credential.getUpdated_at());
        cv.put(TBL_CREDENTIAL.status,credential.getStatus());
        long isSuccess =db.insert(TABLE_NAME,null,cv);
        if(isSuccess >0){
            return "success";
        }else {
            return "Fail";
        }
    }

    @SuppressLint("Range")
    public List<Credential> getAllCredential(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM "+TABLE_NAME;
        List<Credential> listCredential = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                Credential credential = new Credential();
                credential.setId(cursor.getInt(cursor.getColumnIndex(TBL_CREDENTIAL.id)));
                credential.setAccess_token(cursor.getString(cursor.getColumnIndex(TBL_CREDENTIAL.access_token)));
                credential.setRefresh_token(cursor.getString(cursor.getColumnIndex(TBL_CREDENTIAL.refresh_token)));
                credential.setAccount_id(cursor.getInt(cursor.getColumnIndex(TBL_CREDENTIAL.account_id)));
                credential.setExpire_time(cursor.getString(cursor.getColumnIndex(TBL_CREDENTIAL.expire_time)));
                credential.setCreated_at(cursor.getString(cursor.getColumnIndex(TBL_CREDENTIAL.created_at)));
                credential.setUpdated_at(cursor.getString(cursor.getColumnIndex(TBL_CREDENTIAL.updated_at)));
                credential.setStatus(cursor.getInt(cursor.getColumnIndex(TBL_CREDENTIAL.status)));
                listCredential.add(credential);
            }while (cursor.moveToNext());

        }
        return  listCredential;
    }
    public String updateCredential(Credential credential){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv =new ContentValues();
        cv.put(TBL_CREDENTIAL.access_token,credential.getAccess_token());
        cv.put(TBL_CREDENTIAL.refresh_token,credential.getRefresh_token());
        cv.put(TBL_CREDENTIAL.account_id,credential.getAccount_id());
        cv.put(TBL_CREDENTIAL.expire_time,credential.getExpire_time());
        cv.put(TBL_CREDENTIAL.created_at,credential.getCreated_at());
        cv.put(TBL_CREDENTIAL.updated_at,credential.getUpdated_at());
        cv.put(TBL_CREDENTIAL.status,credential.getStatus());
        long isSuccess = db.update(TABLE_NAME, cv, "id="+credential.getId(),null);
       if(isSuccess >0){
           return "Update Success";
       }else {
           return "Update fail";
       }
    }
    public String deleteCredential(int credential_id){
        SQLiteDatabase db = this .getWritableDatabase();
        long isSuccess = db.delete(TABLE_NAME,"id="+credential_id,null);
        if(isSuccess >0){
            return "Delete Success";
        }else {
            return "Delete Fail";
        }
    }

}
