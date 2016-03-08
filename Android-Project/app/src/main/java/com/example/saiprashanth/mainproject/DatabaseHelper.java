package com.example.saiprashanth.mainproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by sagar on 10/20/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="users.db";
    public static final String USER_TABLE="User";
    public static final String COLUMN_USERID="id";
    public static final String COLUMN_NAME="name";
    public static final String COLUMN_AGE="age";
    public static final String COLUMN_PASSWORD="password";
    public static final String COLUMN_ADDRESS="address";
    public static final String COLUMN_ZIP="zip";
    public static final String COLUMN_PHONE="phone";
    public static final String COLUMN_SECURITYQUESTION="question";
    public static final String COLUMN_SECURITYANSWER="answer";

    public static final String ITEM_TABLE="Item";
    public static final String COLUMN_ITEM_ID="itemId";
    public static final String COLUMN_ITEM_NAME="name";
    public static final String COLUMN_ITEM_DESC="description";
    public static final String COLUMN_ITEM_AMOUNT="amount";
    public static final String COLUMN_USER_NAME="username";
    SQLiteDatabase db;

    private static final String USER_TABLE_CREATE="create table if not exists "+ USER_TABLE+"("+COLUMN_USERID+" integer primary key autoincrement,"
            +COLUMN_NAME+" text not null, "+COLUMN_AGE+" text not null, "+COLUMN_PASSWORD+" text not null, "+COLUMN_ADDRESS+" text not null, " +
            COLUMN_ZIP+" text not null,"+COLUMN_SECURITYQUESTION+" text not null,"+COLUMN_SECURITYANSWER+" text not null,"+
            COLUMN_PHONE+" text not null);";

    private static final String ITEM_TABLE_CREATE="create table if not exists "+ITEM_TABLE+"("+COLUMN_ITEM_ID+" integer primary key autoincrement,"+
            COLUMN_PHONE+" text not null, "+COLUMN_ITEM_NAME+" text not null, "+COLUMN_ITEM_DESC+" text not null, "+COLUMN_ITEM_AMOUNT+
            " text not null, foreign key("+COLUMN_PHONE+") references "+USER_TABLE+"("+COLUMN_PHONE+"));";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_TABLE_CREATE);
        db.execSQL(ITEM_TABLE_CREATE);
        this.db=db;
    }

    public void updatePassword(String phone, String password)
    {
        Hashing h=new Hashing();
         String hashedPassword=h.computeSHAHash(password);
         String modifyPassword="update "+USER_TABLE+" set "+COLUMN_PASSWORD+" = '"+hashedPassword+"' where "+ COLUMN_PHONE+ " = '"+phone+"';";
         db=this.getWritableDatabase();
         db.execSQL(modifyPassword);
    }

    public String getName(String attribute, String value) {
        db = this.getReadableDatabase();
        String query = "select "+COLUMN_NAME+" from " + USER_TABLE + " where " + attribute + " = " + value;
        Cursor cursor = db.rawQuery(query, null);
        String a = "not found";

        if(cursor.moveToFirst())
            a = cursor.getString(0);
        return a;

    }

    public String getItemAndSellerDetails(String value1, String value2)
    {
        String result="";
        db=this.getReadableDatabase();
        if(value2.equals(""))
        {
            String query="select "+ITEM_TABLE+"."+ COLUMN_ITEM_ID+","+ITEM_TABLE+"."+ COLUMN_ITEM_NAME+", "+ITEM_TABLE+"."+COLUMN_ITEM_DESC+", " +
                    ITEM_TABLE+"."+COLUMN_ITEM_AMOUNT+", "+USER_TABLE+"."+COLUMN_NAME+", "+USER_TABLE+"."+COLUMN_ADDRESS+", "+
                USER_TABLE+"."+COLUMN_PHONE+" from "+USER_TABLE+", "+ITEM_TABLE+" where "+ITEM_TABLE+"."+
                    COLUMN_PHONE+"="+USER_TABLE+"."+COLUMN_PHONE+" and "+ITEM_TABLE+"."+COLUMN_ITEM_NAME+"='"+value1+"' and "+USER_TABLE+"."+COLUMN_NAME+"!=+'"+Global.name+"';";
            Cursor cursor=db.rawQuery(query,null);
            if(cursor.moveToFirst())
            {
                do{
                    result+="Item ID: "+cursor.getString(0)+"\n"+cursor.getString(1)+"\n"+cursor.getString(2)+"\n$"+cursor.getString(3)+"\nContact: "+cursor.getString(4)+"\nAddress: "+cursor.getString(5)+"\nPhone: "+cursor.getString(6)+"\n\n";
                }while (cursor.moveToNext());
            }
        }
        else if(value1.equals(""))
        {
            String query = "select "+ITEM_TABLE+"."+ COLUMN_ITEM_ID+","+ITEM_TABLE+"."+COLUMN_ITEM_NAME+","+ ITEM_TABLE+"."+COLUMN_ITEM_DESC+", "+ITEM_TABLE+"."+COLUMN_ITEM_AMOUNT+","+
            USER_TABLE+"."+COLUMN_NAME+", "+USER_TABLE+"."+COLUMN_ADDRESS+", "+USER_TABLE+"."+COLUMN_PHONE+" from "+
            USER_TABLE+", "+ITEM_TABLE+" where "+ITEM_TABLE+"."+COLUMN_PHONE+"="+USER_TABLE+"."+COLUMN_PHONE+" and "+
                    USER_TABLE+"."+COLUMN_ZIP+"='" + value2 + "' and "+USER_TABLE+"."+COLUMN_NAME+"!=+'"+Global.name+"';";
            Cursor cursor=db.rawQuery(query,null);
            if(cursor.moveToFirst())
            {
                do{
                    result+="Item ID: "+cursor.getString(0)+"\n"+cursor.getString(1)+"\n"+cursor.getString(2)+"\n$"+cursor.getString(3)+"\nContact: "+cursor.getString(4)+"\nAddress: "+cursor.getString(5)+"\nPhone: "+cursor.getString(6)+"\n\n";
                }while (cursor.moveToNext());
            }
        }
        else {
            String query = "select "+ITEM_TABLE+"."+ COLUMN_ITEM_ID+","+ITEM_TABLE+"."+COLUMN_ITEM_NAME+", "+ITEM_TABLE+"."+COLUMN_ITEM_DESC+", "+ITEM_TABLE+"."+
            COLUMN_ITEM_AMOUNT+", "+USER_TABLE+"."+COLUMN_NAME+", "+USER_TABLE+"."+COLUMN_ADDRESS+", "+USER_TABLE+"."+COLUMN_PHONE+" from "+
            USER_TABLE+", "+ITEM_TABLE+" where "+ITEM_TABLE+"."+COLUMN_PHONE+"="+USER_TABLE+"."+COLUMN_PHONE+" and "+
                    ITEM_TABLE+"."+COLUMN_ITEM_NAME+"='" + value1 + "' and "+USER_TABLE+"."+COLUMN_ZIP+"='" + value2 + "' and "+
                    USER_TABLE+"."+COLUMN_NAME+"!=+'"+Global.name+"';";
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    result+="Item ID: "+cursor.getString(0)+"\n"+cursor.getString(1)+"\n"+cursor.getString(2)+"\n$"+cursor.getString(3)+"\nContact: "+cursor.getString(4)+"\nAddress: "+cursor.getString(5)+"\nPhone: "+cursor.getString(6)+"\n\n";
                } while (cursor.moveToNext());
            }
        }
        return result;
    }

    public String getItemsForSale() {
        String result = "";
        db = this.getReadableDatabase();
        String query = "select "+ITEM_TABLE+"."+COLUMN_ITEM_ID+", "+ITEM_TABLE+"."+COLUMN_ITEM_NAME+","+ITEM_TABLE+"."+COLUMN_ITEM_DESC+
                ","+ITEM_TABLE+"."+COLUMN_ITEM_AMOUNT+" from "+ITEM_TABLE+" where "+ITEM_TABLE+"."+COLUMN_PHONE+"="+Global.phone;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                result += "Item ID: " + cursor.getString(0) + "\n" + cursor.getString(1) + "\n" + cursor.getString(2) + "\n$" + cursor.getString(3) + "\n\n";
            } while (cursor.moveToNext());
        }
        return result;
    }

    public void deleteItem(String id)
    {
        db = this.getReadableDatabase();
        String query="delete from "+ITEM_TABLE+" where "+ITEM_TABLE+"."+COLUMN_ITEM_ID+"='"+id+"';";
        db.execSQL(query);
    }

    public String getItem(String id)
    {
        String result = "";
        db = this.getReadableDatabase();
        String query = "select "+ITEM_TABLE+"."+COLUMN_ITEM_ID+", "+ITEM_TABLE+"."+COLUMN_ITEM_NAME+", "+ITEM_TABLE+"."+COLUMN_ITEM_DESC+", "+
                ITEM_TABLE+"."+COLUMN_ITEM_AMOUNT+" from "+ITEM_TABLE+" where "+ITEM_TABLE+"."+COLUMN_ITEM_ID+"=" + id;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                result += "Item ID: " + cursor.getString(0) + "\n" + cursor.getString(1) + "\n" + cursor.getString(2) + "\n$" + cursor.getString(3) + "\n\n";
            } while (cursor.moveToNext());
        }
        return result;
    }

    public void insertUser(String name,String password, String age,String phone,String address, String zip,String question,String answer)
    {
        Hashing h=new Hashing();
    db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_NAME,name);
        values.put(COLUMN_AGE,age);
        values.put(COLUMN_PASSWORD,h.computeSHAHash(password));
        values.put(COLUMN_ADDRESS,address);
        values.put(COLUMN_ZIP,zip);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_SECURITYQUESTION, question);
        values.put(COLUMN_SECURITYANSWER, answer);
        db.insert(USER_TABLE, null, values);

    }

    public void insertItem(String phone, String name, String description, String amount)
    {
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_PHONE,phone);
        values.put(COLUMN_ITEM_NAME,name);
        values.put(COLUMN_ITEM_DESC,description);
        values.put(COLUMN_ITEM_AMOUNT,amount);
        db.insert(ITEM_TABLE, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    String query="DROP TABLE IF EXISTS"+USER_TABLE;
        String query1="DROP TABLE IF EXISTS"+ITEM_TABLE;
    db.execSQL(query);
        db.execSQL(query1);
    this.onCreate(db);
    }


    public String searchPass(String number)
    {
    db=this.getReadableDatabase();
        String query="select "+COLUMN_PHONE+", "+COLUMN_PASSWORD+" from "+USER_TABLE;
        Cursor cursor=db.rawQuery(query,null);
        String a,b="not found";

        if(cursor.moveToFirst())
        {
            do{
                a=cursor.getString(0);


                if(a.equals(number))
                {
                    b=cursor.getString(1);
                }
            }while (cursor.moveToNext());
        }
        return b;
    }

    public String searchQue(String que, String phone)
    {
        db=this.getReadableDatabase();
        String query="select "+COLUMN_SECURITYQUESTION+", "+COLUMN_SECURITYANSWER+" from "+USER_TABLE+" where "+USER_TABLE+"."+COLUMN_PHONE+"="+phone;
        Cursor cursor=db.rawQuery(query,null);
        String a,b="not found";
        if (cursor.moveToFirst())
        {
                a=cursor.getString(0);
                if(a.equals(que))
                {
                    b=cursor.getString(1);
                }
        }
        return b;
    }
}
