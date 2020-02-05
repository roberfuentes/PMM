package com.example.finalprojectpmm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper
{

    private static final String DB_NAME = "xiaomi.db";

    //Table customer
    private static final String TABLE_CUSTOMER= "CUSTOMER";
    private static final String CUSTOMER_ID = "ID";
    private static final String CUSTOMER_NAME = "NAME";

    private static final String TABLE_ORDERS= "ORDERS";
    private static final String ORDERS_ID = "ID";
    private static final String ORDERS_COST = "COST";
    private static final String ORDERS_CUSTOMER= "CUSTOMER_ID";


    private static final String TABLE_ORDERLINE= "ORDERLINE";
    private static final String ORDERLINE_ID = "ID";
    private static final String ORDERLINE_IMG = "IMG";
    private static final String ORDERLINE_PRICE = "PRICE";
    private static final String ORDERLINE_QUANTITY= "QUANTITY";
    private static final String ORDERLINE_ORDERS= "ORDER_ID";




    private static final int DB_VERSION = 3;
    private static final String CREATE_TABLE_CUSTOMER="CREATE TABLE "
            + TABLE_CUSTOMER+ "(" + CUSTOMER_ID+ " integer primary key autoincrement, "
            + CUSTOMER_NAME+ " text)";
    private static final String CREATE_TABLE_ORDER ="CREATE TABLE "
            + TABLE_ORDERS + "(" + ORDERS_ID +" integer primary key autoincrement, "
            + ORDERS_COST + " integer not null,"
            + ORDERS_CUSTOMER + " integer not null,"
            + " FOREIGN KEY (" + ORDERS_CUSTOMER + ") REFERENCES "+ TABLE_CUSTOMER +"(" + CUSTOMER_ID + "))";

    private static final String CREATE_TABLE_ORDERLINE ="CREATE TABLE "
            + TABLE_ORDERLINE + "(" + ORDERLINE_ID+ " integer primary key autoincrement, "
            + ORDERLINE_IMG + "blob not null,"
            + ORDERLINE_PRICE +" integer not null, "
            + ORDERLINE_QUANTITY + " integer not null, "
            + ORDERLINE_ORDERS + " integer not null, "
            +" FOREIGN KEY (" + ORDERLINE_ORDERS + ") REFERENCES "+TABLE_ORDERS+"(" + ORDERS_ID+ "))";


    public DBHelper(@Nullable Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        createTables(db);
        System.out.println("Does this even work?");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        System.out.println("Does this even work?2");
        deleteTables(db);

        createTables(db);
    }

    public void createTables(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_CUSTOMER);
        db.execSQL(CREATE_TABLE_ORDER);
        db.execSQL(CREATE_TABLE_ORDERLINE);
    }

    public void deleteTables(SQLiteDatabase db){

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERLINE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER);
    }

    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        if(db!= null){
            Cursor data = db.rawQuery("Select * from customer", null);
            return data;
        }
        return null;
    }

    public long insertOrderLine(ContentValues val){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            return db.insert(TABLE_ORDERLINE, null, val);
        }catch(Exception e){
            e.printStackTrace();

        }
        return 0;

    }
}
