package com.example.malli.myapplication.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by malli on 3/9/2016.
 */
public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 10;

    // Database Name
    private static final String DATABASE_NAME = "android_api";

    // Login table name
    private static final String TABLE_CART = "cart";
    private static final String TABLE_TOTAL_COST = "totalcost";
    private static final String TABLE_QTY = "qty";

    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_QTY = "qty";
    private static final String KEY_COST = "cost";
    private static final String KEY_TOTAlCOST = "totalcost";
   // private static final String KEY_QTY = "qty";
    //private static final String KEY_CREATED_AT = "created_at";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_CART + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_QTY + " TEXT," + KEY_COST + " TEXT"
                + ");";
        db.execSQL(CREATE_LOGIN_TABLE);
        String CREATE_TOTAL_COST = "CREATE TABLE " + TABLE_TOTAL_COST + "("
               + KEY_TOTAlCOST + " TEXT" + ");";
        db.execSQL(CREATE_TOTAL_COST);
        String CREATE_QTY = "CREATE TABLE " + TABLE_QTY + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_QTY + " TEXT" + ");";
        db.execSQL(CREATE_QTY);
        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TOTAL_COST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QTY);
        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public void addUser(String name, String qty, String cost) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name); // Name
        values.put(KEY_QTY, qty); // Email
        values.put(KEY_COST, cost); // Email
        // Inserting Row
        long id = db.insert(TABLE_CART, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    public void add(String name, String qty, String cost,String itemid) {
        long id = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("name", name);
        Log.d("itemid", itemid);
        String selectQuery = "SELECT  * FROM " + TABLE_CART + " WHERE " + KEY_NAME + " =  '"+ name +"' " ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        Integer tc = 0;
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            Log.d("vvvvvv", "vvvvvvv");
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, name); // Name
            values.put(KEY_QTY, qty); // Email
            values.put(KEY_COST, cost);
            values.put(KEY_ID, itemid);// Email
            db.update(TABLE_CART, values, KEY_NAME + "= '" + name + "'", null);
        }else {
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, name); // Name
            values.put(KEY_QTY, qty); // Email
            values.put(KEY_COST, cost); // Email
            values.put(KEY_ID,itemid);
            // Inserting Row
            id = db.insert(TABLE_CART, null, values);
            Log.d(TAG, "New user inserted into sqlite: " + id);
            Log.d("values", String.valueOf(values));
        }
            db.close(); // Closing database connection
            getUserDetails();
    }


    public void addtotalcost(String totalcost) {
           Log.d("totalcost", totalcost);
            Integer tc = 0;
            ContentValues values = new ContentValues();

                 tc = Integer.valueOf(totalcost);

        values.put(KEY_TOTAlCOST, String.valueOf(tc));
            SQLiteDatabase db = this.getWritableDatabase();

            // Inserting Row
             db.insert(TABLE_TOTAL_COST, null, values);
             Log.d("values_totalcost", String.valueOf(values));

            db.close(); // Closing database connection
           //  getUserDetails();
        }

    public void deletetotalcost(String totalcost) {
        Log.d("totalcost", totalcost);
        Integer tc = 0;
        ContentValues values = new ContentValues();
        String total = getTotalCost2();
        tc = Integer.valueOf(totalcost);
        tc = Integer.valueOf(total)-Integer.valueOf(tc);
        values.put(KEY_TOTAlCOST, String.valueOf(tc));
        SQLiteDatabase db = this.getWritableDatabase();

        // Inserting Row
        db.insert(TABLE_TOTAL_COST, null, values);
        Log.d("values_totalcost", String.valueOf(values));

        db.close(); // Closing database connection
        //  getUserDetails();
    }
/*
    public void addqty(String id,String qty) {
        Log.d("qty",qty);
       // Integer tc = 0;
        ContentValues values = new ContentValues();
        //tc = Integer.valueOf(totalcost) + Integer.valueOf(getTotalCost2());
        values.put(KEY_ID, id);
        values.put(KEY_QTY, qty);
        SQLiteDatabase db = this.getWritableDatabase();

        // Inserting Row
        db.insert(TABLE_QTY, null, values);
        Log.d("values_QTY", String.valueOf(values));

        db.close(); // Closing database connection
        //  getUserDetails();
    }*/
    public void addqty(String id,String qty) {
        //long id = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        //Log.d("name", name);
        Log.d("qty", qty);
        String selectQuery = "SELECT  * FROM " + TABLE_QTY + " WHERE " + KEY_ID + " =  '"+ id +"' " ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            ContentValues values = new ContentValues();
            values.put(KEY_QTY, qty); // Email
            db.update(TABLE_QTY, values, KEY_ID + "= '" + id + "'", null);
            Log.d("after +"+id, String.valueOf(values));
        }else {
            Log.d("qqqq","qqqq");
            ContentValues values = new ContentValues();

            values.put(KEY_QTY, qty); // Email

            values.put(KEY_ID, id);
            // Inserting Row
             db.insert(TABLE_QTY, null, values);
            Log.d("values of addqty", String.valueOf(values));

        }
        db.close(); // Closing database connection
        getUserDetails();
    }


    public Integer gettotalcost2() {
        List<String> items = new ArrayList<String>();
        HashMap<String, String> order = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_CART;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        Integer i = cursor.getCount();
        Log.d("count", String.valueOf(i));
        Integer tc = 0;
        if (cursor.moveToFirst()) {
            do {
                order.put("name", cursor.getString(1));
                order.put("qty", cursor.getString(2));
                order.put("cost", cursor.getString(3));
                //  order.put("created_at", cursor.getString(4));
                tc = tc + Integer.valueOf(cursor.getString(2))*Integer.valueOf(cursor.getString(3));
                items.add(String.valueOf(order));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + items);
        Log.d("tc",String.valueOf(tc));
        return tc;
    }

    /**
     * Getting user data from database
     * */
    public List<String> getUserDetails() {
        List<String> items = new ArrayList<String>();
        HashMap<String, String> order = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_CART;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        Integer i = cursor.getCount();
       Log.d("count", String.valueOf(i));
        if (cursor.moveToFirst()) {
            do {
            order.put("name", cursor.getString(1));
            order.put("qty", cursor.getString(2));
            order.put("cost", cursor.getString(3));
          //  order.put("created_at", cursor.getString(4));
                items.add(String.valueOf(order));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + items);

        return items;
    }

    public JSONArray getUserDetails2() throws JSONException {
        //Create values JSONArray
        JSONArray valuesarray = new JSONArray();
        String selectQuery = "SELECT  * FROM " + TABLE_CART;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.moveToFirst()) {
            do {
            // Create values jsonObject
            JSONObject valuesJson = new JSONObject();
            valuesJson.put("name",cursor.getString(1));
            valuesJson.put("qty",cursor.getString(2));
            valuesJson.put("cost",cursor.getString(3));
            valuesJson.put("id",cursor.getString(0));
            // put values jsonObject to valuesarray JSONArray
            valuesarray.put(valuesJson);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return valuesarray;
     /*   List<String> items = new ArrayList<String>();
        HashMap<String, String> order = new HashMap<String, String>();
       String selectQuery = "SELECT  * FROM " + TABLE_CART;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        Integer i = cursor.getCount();
        Log.d("count", String.valueOf(i));
        if (cursor.moveToFirst()) {
            do {
                order.put("name", cursor.getString(1));
                order.put("qty", cursor.getString(2));
                order.put("cost", cursor.getString(3));
                //  order.put("created_at", cursor.getString(4));
                Log.d("9999","9999");
                items.add(String.valueOf(order));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + items);

        return items;
  */  }

    public    HashMap<String,String> getDetails() {
     //   List<String> items = new ArrayList<String>();
        HashMap<String,String> order = new HashMap<String,String>();
        String selectQuery = "SELECT  * FROM " + TABLE_CART ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
       Integer i = cursor.getCount();
       Integer j =0;
        Log.d("count", String.valueOf(i));
        if (cursor.moveToFirst()) {
            do {
                order.put("name", cursor.getString(1));
                order.put("qty", cursor.getString(2));
                order.put("cost", cursor.getString(3));
               // order.put("id",cursor.getString(4));

//                order.put(j,Integer.valueOf(cursor.getString(2)));
                j++;
                //order.put("name", cursor.getString(1));
                //order.put("qty", Integer.valueOf(cursor.getString(2)));
               // order.put("cost", cursor.getString(3));
                //  order.put("created_at", cursor.getString(4));
               // Log.d("9999", "9999");
       //         items.add(String.valueOf(order));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite22: " + order);

        return order;
    }

    public   String getTotalCost2() {
        HashMap<String, String> order = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_TOTAL_COST;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
       // cursor.moveToFirst();
        cursor.moveToLast();
        if (cursor.getCount() > 0) {
            order.put("totalcost",cursor.getString(0));
        }else{
            order.put("totalcost", String.valueOf(0));
        }

        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + order.toString());

        //if(cursor.getCount()>0)
        Log.d("totalcost1",String.valueOf(order.get("totalcost")));
        return order.get("totalcost");

    }

    public   String getUser(Integer j) {
        HashMap<String, String> order = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_CART + " WHERE " + KEY_ID + " =  '"+ j +"' " ;;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
       cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            order.put("name", cursor.getString(1));
            order.put("qty", cursor.getString(2));
            order.put("cost", cursor.getString(3));
            order.put("id", cursor.getString(0));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + order.toString());

        //if(cursor.getCount()>0)
        Log.d("qty"+j,String.valueOf(order.get("qty")));
            return order.get("qty");

    }


    public   String getQty(Integer j) {
        HashMap<String, String> order = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_QTY + " WHERE " + KEY_ID + " =  '"+ j +"' " ;;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            //order.put("name", cursor.getString(1));
            order.put("qty", cursor.getString(1));
            //order.put("cost", cursor.getString(3));
            //order.put("id", cursor.getString(0));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching qty from Sqlite2: " + order.toString());

        //if(cursor.getCount()>0)
        Log.d("qty"+j,String.valueOf(order.get("qty")));
        return order.get("qty");

    }
    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteQty() {
        SQLiteDatabase db = this.getWritableDatabase();
        //File database=getApplicationContext().getDatabasePath("databasename.db");

        // Delete All Rows
        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + TABLE_QTY + "'", null);

        if (cursor != null) {
            db.execSQL("delete from " + TABLE_QTY);
            cursor.close();
        }
    }
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        //File database=getApplicationContext().getDatabasePath("databasename.db");

        // Delete All Rows
        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + TABLE_CART + "'", null);

        if (cursor != null) {
            db.execSQL("delete from "+ TABLE_CART);
            cursor.close();
        }
        Cursor cursor1 = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + TABLE_TOTAL_COST + "'", null);

        if (cursor1 != null) {
            db.execSQL("delete from "+ TABLE_TOTAL_COST);
            cursor1.close();
        }

        Log.d(TAG, "Deleted all user info from sqlite");

    }
    public void deleteTable() {
        SQLiteDatabase db = this.getReadableDatabase();

        if (db == null || !db.isOpen())
            db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
    }

}