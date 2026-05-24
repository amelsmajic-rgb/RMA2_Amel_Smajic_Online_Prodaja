package com.example.rma_namjestaj_n_m.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "shop.db";
    private static final int DATABASE_VERSION = 4;

    public static final String TABLE_PRODUCTS = "products";
    public static final String TABLE_ORDERS = "orders";
    public static final String TABLE_USERS = "users";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String usersTable =
                "CREATE TABLE " + TABLE_USERS + " (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "isAdmin INTEGER DEFAULT 0 ," +
                        "isSuperAdmin INTEGER DEFAULT 0 ," +
                        "firstName TEXT," +
                        "lastName TEXT," +
                        "email TEXT UNIQUE," +
                        "address TEXT," +
                        "password TEXT," +
                        "phone TEXT)";
        String createProductsTable =
                "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT," +
                        "description TEXT," +
                        "price REAL," +
                        "image TEXT)";

        String createOrdersTable =
                "CREATE TABLE " + TABLE_ORDERS + "(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "userId INTEGER," +
                        "totalPrice REAL," +
                        "orderDate TEXT)";

        db.execSQL(usersTable);
        db.execSQL(createProductsTable);
        db.execSQL(createOrdersTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            String createOrdersTable =
                    "CREATE TABLE " + TABLE_ORDERS + "(" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "userId INTEGER," +
                            "totalPrice REAL," +
                            "orderDate TEXT)";
            db.execSQL(createOrdersTable);
        }
        if (oldVersion < 4) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
            String createProductsTable =
                    "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "name TEXT," +
                            "description TEXT," +
                            "price REAL," +
                            "image TEXT)";
            db.execSQL(createProductsTable);
        }
    }
}