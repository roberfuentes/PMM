package com.example.finalprojectpmm.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.example.finalprojectpmm.Models.Customer;
import com.example.finalprojectpmm.Models.Order;
import com.example.finalprojectpmm.Models.OrderLine;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper
{

    SQLiteDatabase db = null;
    private static final String DB_NAME = "xiaomi.db";
    Context context;
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
    private static final String ORDERLINE_INSURANCE = "INSURANCE";
    private static final String ORDERLINE_HEADPHONES = "HEADPHONES";
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
            + ORDERLINE_INSURANCE + " integer not null,"
            + ORDERLINE_HEADPHONES + " integer not null,"
            + ORDERLINE_IMG + " blob not null,"
            + ORDERLINE_ORDERS + " integer not null, "
            +" FOREIGN KEY (" + ORDERLINE_ORDERS + ") REFERENCES "+TABLE_ORDERS+"(" + ORDERS_ID+ "))";


    public DBHelper(@Nullable Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
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


    public void retrieveCustomerData(){
        db = this.getWritableDatabase();

        Cursor c = db.rawQuery("Select * from customer", null);
        while(c.moveToNext()){
            int id = c.getInt(0);
            String name= c.getString(1);
            System.out.println("Customer: id:" + id + " name:"+name);
        }
    }

    public void retrieveOrderData(){
        db = this.getWritableDatabase();

        Cursor c = db.rawQuery("Select * from orders", null);
        while(c.moveToNext()){
            int id = c.getInt(0);
            int cost= c.getInt(1);
            int customer_id= c.getInt(1);
            System.out.println("Orders: id:" + id + " cost:"+cost + ", customer_id:" + customer_id);
        }
    }

    public void retrieveOrderLineData(){
        db = this.getWritableDatabase();

        Cursor c = db.rawQuery("Select * from orderline", null);
        while(c.moveToNext()){

            int id = c.getInt(0);
            int price= c.getInt(1);
            int quantity= c.getInt(2);
            int order_id= c.getInt(4);
            System.out.println("Orders: id:" + id + " price:"+price+ " quantity:"+ quantity + "  order_id:" + order_id);
        }
    }
    public Bitmap retrieveOrderLineDataImage(){
        db = this.getWritableDatabase();

        Cursor c = db.rawQuery("Select * from orderline", null);
        Bitmap bt = null;
        while(c.moveToNext()){
            byte[] image= c.getBlob(3);
            bt = BitmapFactory.decodeByteArray(image, 0, image.length);
    }
        return bt;
}


//STARTS HERE

    //INSERT
    public long insertOrderLine(int price, int quantity, boolean insurance, boolean headphones, int image, long orderID){
        try{

            //Convert image to byte[] so DB can accept it
            /*ByteBuffer bytebuffer = ByteBuffer.allocate(image);
            byte[] byteImage = bytebuffer.array();*/

            //ERROR WITH THE BYTE, NOT SOLVED MEMORY PROBLEM??
            Bitmap bitImage = BitmapFactory.decodeResource(context.getResources(), image);
            byte[] byteImage = getBitmapAsByteArray(bitImage);
            int dbInsurance=0;
            int dbHeadphones=0;
            if(insurance){
                dbInsurance=1;
            }
            if(headphones){
                dbHeadphones=1;
            }


            ContentValues values = new ContentValues();
            values.put(ORDERLINE_PRICE, price);
            values.put(ORDERLINE_QUANTITY, quantity);
            values.put(ORDERLINE_INSURANCE, dbInsurance);
            values.put(ORDERLINE_HEADPHONES, dbHeadphones);
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

            values.put(ORDERS_CUSTOMER, customerID);
            values.put(ORDERS_COST, cost);


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


    //RETRIEVE


    public ArrayList<Customer> retrieveCustomers(){
        db = this.getWritableDatabase();
        ArrayList<Customer> customers= new ArrayList<>();

        Cursor c = db.rawQuery("Select * from " + TABLE_CUSTOMER, null);
        while(c.moveToNext()){
            Customer customer = new Customer(c.getInt(0), c.getString(1));
            customers.add(customer);
        }

        return customers;
    }

    public ArrayList<Order> retrieveOrder(int findCustomer){
        db = this.getWritableDatabase();
        ArrayList<Order> orders = new ArrayList<>();
        Cursor c = null;

        if(findCustomer!=-1){
            c = db.rawQuery("Select * from "+ TABLE_ORDERS + " where " + ORDERS_CUSTOMER +"="+ findCustomer, null);
        }else{
            c = db.rawQuery("Select * from "+ TABLE_ORDERS, null);
        }

        while(c.moveToNext()){

            int id = c.getInt(0);
            String cost = c.getString(1);
            int custID = c.getInt(2);

            Order order = new Order(id, custID, cost);
            orders.add(order);
        }

        return orders;
    }


    public ArrayList<OrderLine> retrieveOrderLine(int findOrder){
        db = this.getWritableDatabase();
        ArrayList<OrderLine> orderLines = new ArrayList<>();
        Cursor c = null;

        if(findOrder!=-1){
            c = db.rawQuery("Select * from "+ TABLE_ORDERLINE + " where "+ORDERLINE_ORDERS+"="+findOrder, null);
        }else{
            c = db.rawQuery("Select * from "+ TABLE_ORDERLINE, null);
        }


        while(c.moveToNext()){



            int id = c.getInt(0);
            String price = Integer.toString(c.getInt(1));
            String quantity = Integer.toString(c.getInt(2));
            int boolInsurance= c.getInt(3);
            int boolHeadphones = c.getInt(4);
            byte[] image= c.getBlob(5);
            int order_id = c.getInt(6);

            Bitmap bt = BitmapFactory.decodeByteArray(image, 0, image.length);

            boolean insurance = false;
            boolean headphones = false;

            if(boolInsurance==1){
                insurance = true;
            }
            if(boolHeadphones==1){
                headphones = true;
            }

            OrderLine orderLine = new OrderLine(id,price, quantity, insurance, headphones, bt,order_id);
            orderLines.add(orderLine);
        }

        return orderLines;
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Bitmap resized = Bitmap.createScaledBitmap(bitmap, 300, 200, true);

        resized.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }




}