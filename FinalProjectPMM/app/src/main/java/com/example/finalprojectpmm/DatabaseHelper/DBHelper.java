package com.example.finalprojectpmm.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;


import com.example.finalprojectpmm.Models.Customer;
import com.example.finalprojectpmm.Models.Order;
import com.example.finalprojectpmm.Models.OrderLine;
import com.example.finalprojectpmm.Models.Products;
import com.example.finalprojectpmm.R;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper
{

    Products products[] = {new Products("Xiaomi redmi 7", 150, R.drawable.xiaomi_redmi_note_7),
            new Products("Xiaomi redmi 8", 200, R.drawable.xiaomi_redmi_note_8),
            new Products("Xiaomi redmi 9 lite", 250, R.drawable.xiaomi_mi_9_lite),
            new Products("Xiaomi redmi 9t", 300, R.drawable.xiaomi_mi_9t)};

    private SQLiteDatabase db = null;
    private static final String DB_NAME = "xiaomi.db";
    private Context context;
    //Table customer
    private static final String TABLE_CUSTOMER = "CUSTOMER";
    private static final String CUSTOMER_ID = "ID";
    private static final String CUSTOMER_USERNAME = "USERNAME";
    private static final String CUSTOMER_PASSWORD = "PASSWORD";

    private static final String TABLE_ORDERS = "ORDERS";
    private static final String ORDERS_ID = "ID";
    private static final String ORDERS_COST = "COST";
    private static final String ORDERS_CUSTOMER = "CUSTOMER_ID";


    private static final String TABLE_ORDERLINE = "ORDERLINE";
    private static final String ORDERLINE_ID = "ID";
    private static final String ORDERLINE_PRICE = "PRICE";
    private static final String ORDERLINE_QUANTITY = "QUANTITY";
    private static final String ORDERLINE_INSURANCE = "INSURANCE";
    private static final String ORDERLINE_ORDERS = "ORDER_ID";
    private static final String ORDERLINE_PRODUCTS = "PRODUCTS_ID";
    private static final String ORDERLINE_IMG = "IMG";


    private static final String TABLE_PRODUCTS = "PRODUCTS";
    private static final String PRODUCTS_ID = "ID";
    private static final String PRODUCTS_NAME = "NAME";
    private static final String PRODUCTS_UNIT_PRICE = "UNIT_PRICE";
    private static final String PRODUCTS_IMG = "IMG";


    private static final int DB_VERSION = 3;
    private static final String CREATE_TABLE_CUSTOMER = "CREATE TABLE "
            + TABLE_CUSTOMER + "(" + CUSTOMER_ID + " integer primary key autoincrement, "
            + CUSTOMER_USERNAME + " text unique,"
            + CUSTOMER_PASSWORD + " text)";

    private static final String CREATE_TABLE_ORDER = "CREATE TABLE "
            + TABLE_ORDERS + "(" + ORDERS_ID + " integer primary key autoincrement, "
            + ORDERS_COST + " integer not null,"
            + ORDERS_CUSTOMER + " integer not null,"
            + " FOREIGN KEY (" + ORDERS_CUSTOMER + ") REFERENCES " + TABLE_CUSTOMER + "(" + CUSTOMER_ID + "))";


    private static final String CREATE_TABLE_ORDERLINE = "CREATE TABLE "
            + TABLE_ORDERLINE + "(" + ORDERLINE_ID + " integer primary key autoincrement, "
            + ORDERLINE_PRICE + " integer not null, "
            + ORDERLINE_QUANTITY + " integer not null, "
            + ORDERLINE_INSURANCE + " integer not null,"
            + ORDERLINE_IMG + " blob not null,"
            + ORDERLINE_ORDERS + " integer not null, "
            + ORDERLINE_PRODUCTS + " integer not null,"
            + " FOREIGN KEY (" + ORDERLINE_ORDERS + ") REFERENCES " + TABLE_ORDERS + "(" + ORDERS_ID + "),"
            + " FOREIGN KEY (" + ORDERLINE_PRODUCTS + ") REFERENCES " + TABLE_PRODUCTS + "(" + PRODUCTS_ID + "))";


    private static final String CREATE_TABLE_PRODUCTS = "CREATE TABLE "
            + TABLE_PRODUCTS + "(" + PRODUCTS_ID + " integer primary key autoincrement, "
            + PRODUCTS_NAME + " text unique not null, "
            + PRODUCTS_UNIT_PRICE + " integer not null, "
            + PRODUCTS_IMG + " blob not null)";


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


    private void createTables(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_CUSTOMER);
        db.execSQL(CREATE_TABLE_ORDER);
        db.execSQL(CREATE_TABLE_PRODUCTS);
        db.execSQL(CREATE_TABLE_ORDERLINE);


    }

    private void deleteTables(SQLiteDatabase db)
    {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERLINE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER);

    }


//STARTS HERE


    //CUSTOMER
    public long registerCustomer(String username, String password)
    {
        try
        {
            db = this.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(CUSTOMER_USERNAME, username);
            values.put(CUSTOMER_PASSWORD, password);

            return db.insert(TABLE_CUSTOMER, null, values);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return -1;
    }


    public int getCustomerId(String username, String password)
    {
        db = this.getWritableDatabase();
        int idCustomer = -1;

        Cursor c = db.rawQuery("Select * from " + TABLE_CUSTOMER + " where " + CUSTOMER_USERNAME + " like '" + username + "'", null);
        if (c.getCount() > 0)
        {
            c.moveToFirst();
            String checkPassword = c.getString(2);
            if (checkPassword.equals(password))
            {
                Log.d("checkLoginCustomer", "returns id");
                idCustomer = c.getInt(0);
                return idCustomer;
            }
        }
        c.close();
        return idCustomer;
    }

    public Customer getCustomerUsername(int id)
    {
        db = this.getWritableDatabase();
        String query = "Select " + CUSTOMER_USERNAME + " from " + TABLE_CUSTOMER + " where id=" + id;
        Cursor c = db.rawQuery(query, null);

        String name = "";
        String password = "";
        if (c.getCount() == 1)
        {
            c.moveToFirst();
            name = c.getString(0);
        }
        Customer customer = new Customer(name);
        return customer;
    }

    public ArrayList<Customer> retrieveCustomers()
    {
        db = this.getWritableDatabase();
        ArrayList<Customer> customers = new ArrayList<>();

        Cursor c = db.rawQuery("Select * from " + TABLE_CUSTOMER, null);
        while (c.moveToNext())
        {
            Customer customer = new Customer(c.getInt(0), c.getString(1));
            customers.add(customer);
        }

        c.close();


        return customers;
    }


    //ORDER


    public long insertOrder(long customerID, int cost)
    {
        db = this.getWritableDatabase();
        try
        {
            ContentValues values = new ContentValues();

            values.put(ORDERS_CUSTOMER, customerID);
            values.put(ORDERS_COST, cost);


            return db.insert(TABLE_ORDERS, null, values);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }


    public ArrayList<Order> retrieveOrder(int findCustomer)
    {
        db = this.getWritableDatabase();
        ArrayList<Order> orders = new ArrayList<>();
        Cursor c;

        if (findCustomer != -1)
        {
            c = db.rawQuery("Select * from " + TABLE_ORDERS + " where " + ORDERS_CUSTOMER + "=" + findCustomer, null);
        } else
        {
            c = db.rawQuery("Select * from " + TABLE_ORDERS, null);
        }

        while (c.moveToNext())
        {

            int id = c.getInt(0);
            String cost = c.getString(1);
            int custID = c.getInt(2);

            Order order = new Order(id, custID, cost);
            orders.add(order);
        }
        c.close();
        return orders;
    }

    public boolean deleteOrder(int order_id)
    {
        return db.delete(TABLE_ORDERS, ORDERS_ID+ "=" + order_id, null) > 0;
    }

    //ORDERLINE


    public ArrayList<OrderLine> retrieveOrderLine(int findOrder)
    {
        db = this.getWritableDatabase();
        ArrayList<OrderLine> orderLines = new ArrayList<>();
        Cursor c;

        if (findOrder != -1)
        {
            c = db.rawQuery("Select * from " + TABLE_ORDERLINE + " where " + ORDERLINE_ORDERS + "=" + findOrder, null);
        } else
        {
            c = db.rawQuery("Select * from " + TABLE_ORDERLINE, null);
        }


        while (c.moveToNext())
        {

            int id = c.getInt(0);
            String price = Integer.toString(c.getInt(1));
            String quantity = Integer.toString(c.getInt(2));
            int boolInsurance = c.getInt(3);
            byte[] image = c.getBlob(4);
            int order_id = c.getInt(5);

            Bitmap bt = BitmapFactory.decodeByteArray(image, 0, image.length);

            boolean insurance = false;
            boolean headphones = false;

            if (boolInsurance == 1)
            {
                insurance = true;
            }

            OrderLine orderLine = new OrderLine(id, price, quantity, insurance, bt, order_id);
            orderLines.add(orderLine);
        }

        c.close();
        return orderLines;
    }

    public long insertOrderLine(int price, int quantity, boolean insurance, int productID, long orderID)
    {
        db = this.getWritableDatabase();
        try
        {

            //ERROR WITH THE BYTE, NOT SOLVED MEMORY PROBLEM??
            //SOLVED
            System.out.println("Conseguimos producto que es" + productID);

            Products product = this.retrieveProduct(productID);

            Bitmap bitImage = product.getbitImg();
            byte[] byteImage = getBitmapAsByteArray(bitImage);

            int dbInsurance = 0;
            int dbHeadphones = 0;
            if (insurance)
            {
                dbInsurance = 1;
            }


            ContentValues values = new ContentValues();
            values.put(ORDERLINE_PRICE, price);
            values.put(ORDERLINE_QUANTITY, quantity);
            values.put(ORDERLINE_INSURANCE, dbInsurance);
            values.put(ORDERLINE_IMG, byteImage);
            values.put(ORDERLINE_ORDERS, orderID);
            values.put(ORDERLINE_PRODUCTS, productID);

            return db.insert(TABLE_ORDERLINE, null, values);
        } catch (Exception e)
        {
            e.printStackTrace();

        }
        return 0;
    }

    public boolean deleteOrderLine(int order_id)
    {
        return db.delete(TABLE_ORDERLINE, ORDERLINE_ORDERS+ "=" + order_id, null) > 0;
    }


    //PRODUCTS

    public void insertProducts()
    {

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        for (Products product : products)
        {

            Log.i("has product'", product.getName());

            Bitmap bitImage = BitmapFactory.decodeResource(context.getResources(), product.getimgNotProcessed());
            byte[] byteImage = getBitmapAsByteArray(bitImage);

            values.put(PRODUCTS_NAME, product.getName());
            values.put(PRODUCTS_UNIT_PRICE, product.getUnitPrice());
            values.put(PRODUCTS_IMG, byteImage);

            db.insert(TABLE_PRODUCTS, null, values);
        }


    }


    public Products retrieveProduct(int find_id)
    {
        Products product = null;
        db = this.getWritableDatabase();

        String query2 = "select * from " + TABLE_PRODUCTS;
        Cursor c2;
        c2 = db.rawQuery(query2, null);
        System.out.println("Hay " + c2.getCount() + " moviles dentro");

        String query = "select * from " + TABLE_PRODUCTS + " where id=" +find_id;
        Cursor c;
        c = db.rawQuery(query, null);
        System.out.println(find_id +": Cuenta que consigue" + c.getCount());
        if (c.getCount() > 0)
        {
            c.moveToFirst();

            System.out.println("col0:" + c.getInt(0) + ", col1: " + c.getString(1));
            int id = c.getInt(0);
            String name = c.getString(1);
            int unitPrice = c.getInt(2);
            byte[] image = c.getBlob(3);

            Bitmap bt = BitmapFactory.decodeByteArray(image, 0, image.length);

            product = new Products(id, name, unitPrice, bt);
            System.out.println("before send" + product.getName());

            return product;
        }
        return product;
    }

    public boolean checkProducts()
    {
        db = this.getWritableDatabase();
        String query = "select * from " + TABLE_PRODUCTS;
        Cursor c;
        Log.i("start", "start");
        c = db.rawQuery(query, null);
        if(c.getCount() < 1){
            System.out.println("hay"+c.getCount());
            return true;
        }
        System.out.println("hay"+c.getCount());

        return false;
    }

    private static byte[] getBitmapAsByteArray(Bitmap bitmap)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Bitmap resized = Bitmap.createScaledBitmap(bitmap, 300, 200, true);

        resized.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
}