package com.example.sqlitetutorial.DataBaseClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.sqlitetutorial.Modal.User;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DBNAME="User";
    private static final String TABLENAME="UserInformation";
    private static final String ID="id";
    private static final String USERNAME="username";
    private static final String PASSWORD="password";
    private static final String IMAGE="image";
    private User user;
    private List<User> mUserList = new ArrayList<>();
    private static final String TAG = "DataBaseHelper".getClass().getName();
    String name,password,image;
    int id;

    public DataBaseHelper(@Nullable Context context) {
        super(context, DBNAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE '"+TABLENAME+"'('"+ID+"' INTEGER primary key autoincrement ,'"+USERNAME+"' TEXT ,'"+PASSWORD+"' TEXT,'"+IMAGE+"' TEXT)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLENAME);
        onCreate(db);

    }
    public boolean insert(String name ,String pass ,String image)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME,name);
        contentValues.put(PASSWORD,pass);
        contentValues.put(IMAGE,image);
        long ex=db.insert(TABLENAME,null,contentValues);
        if (ex!=-1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public List<User> read(Context context)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res =db.rawQuery("SELECT * from "+TABLENAME,null);


        if (res.moveToFirst())
        {
            do {
                id = res.getInt(res.getColumnIndex(ID));
                name = res.getString(res.getColumnIndex(USERNAME));
                password = res.getString(res.getColumnIndex(PASSWORD));
                image = res.getString(res.getColumnIndex(IMAGE));
                user = new User(id,name,password,image);
                mUserList.add(user);
            }while (res.moveToNext());
            res.close();

        }

        return mUserList;
    }
    public boolean deleteRecord(int id)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        int cont=db.delete(TABLENAME,"id="+id,null);
        if (cont > 0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }
    public boolean updateRecord(String id,String name ,String password,String image)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,Integer.parseInt(id));
        contentValues.put(USERNAME,name);
        contentValues.put(PASSWORD,password);
        contentValues.put(IMAGE,image);
        long res=db.update(TABLENAME,contentValues,"id=?",new String[]{id});
        if (res!=-1)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    public List<User> findData(String name)
    {
        SQLiteDatabase db=this.getReadableDatabase();


        String sql ="Select * from "+TABLENAME+" where id="+name;
        Cursor res=db.rawQuery(sql,null);


        if (res.moveToFirst())
        {
            do {
                id = res.getInt(res.getColumnIndex(ID));
                name = res.getString(res.getColumnIndex(USERNAME));
                password = res.getString(res.getColumnIndex(PASSWORD));
                image = res.getString(res.getColumnIndex(IMAGE));
                user = new User(id,name,password,image);
                mUserList.add(user);
            }while (res.moveToNext());
            res.close();

        }
        return mUserList;
    }
}

