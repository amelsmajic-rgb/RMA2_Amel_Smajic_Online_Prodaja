package com.example.rma_namjestaj_n_m.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rma_namjestaj_n_m.models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    private DatabaseHelper dbHelper;

    public OrderRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void insertOrder(Order order) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("userId", order.getUserId());
        values.put("totalPrice", order.getTotalPrice());
        values.put("orderDate", order.getOrderDate());

        db.insert(DatabaseHelper.TABLE_ORDERS, null, values);

        db.close();
    }

    public List<Order> getUserOrders(int userId) {

        List<Order> orders = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + DatabaseHelper.TABLE_ORDERS + " WHERE userId=?",
                new String[]{String.valueOf(userId)}
        );

        if(cursor.moveToFirst()) {

            do {

                Order order = new Order(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getDouble(2),
                        cursor.getString(3)
                );

                orders.add(order);

            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return orders;
    }
}