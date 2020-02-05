package com.example.finalprojectpmmubuntu;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;

public class DBHelper extends SQLiteOpenHelper
{

    SQLiteDatabase db = null;
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
            + ORDERLINE_PRICE +" integer not null, "
            + ORDERLINE_QUANTITY + " integer not null, "
            + ORDERLINE_IMG + " blob not null,"
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

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
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
        if(db!= null){
            Cursor data = db.rawQuery("Select * from customer", null);
            return data;
        }
        return null;
    }

    public long insertOrderLine(long orderID, int price, int quantity, int image){
        try{

            //Convert image to byte[] so DB can accept it
            /*ByteBuffer bytebuffer = ByteBuffer.allocate(image);
            byte[] byteImage = bytebuffer.array();*/

            //ERROR WITH THE BYTE, NOT SOLVED MEMORY PROBLEM??
            BigInteger bigInt = BigInteger.valueOf(image);
            byte[] byteImage = bigInt.toByteArray();



            ContentValues values = new ContentValues();
            values.put(ORDERLINE_PRICE, price);
            values.put(ORDERLINE_QUANTITY, quantity);
            values.put(ORDERLINE_IMG, byteImage);
            values.put(ORDERLINE_ORDERS, orderID);

            return db.insert(TABLE_ORDERLINE, null, values);
        }catch(Exception e){
            e.printStackTrace();

        }
        return 0;
    }

    public long insertOrder(long customerID, int cost){
        try{


            ContentValues values = new ContentValues();

            values.put(ORDERS_COST, cost);
            values.put(ORDERS_CUSTOMER, customerID);

            return db.insert(TABLE_ORDERS, null, values);
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public long insertCustomer(String name){
        try{
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(CUSTOMER_NAME, name);

            return db.insert(TABLE_CUSTOMER, null, values);
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;

    }
}
