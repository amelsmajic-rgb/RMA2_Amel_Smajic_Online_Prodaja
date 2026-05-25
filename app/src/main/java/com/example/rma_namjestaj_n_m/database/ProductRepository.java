package com.example.rma_namjestaj_n_m.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rma_namjestaj_n_m.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private DatabaseHelper dbHelper;

    public ProductRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void insertProduct(Product product) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("name", product.getName());
        values.put("description", product.getDescription());
        values.put("price", product.getPrice());
        values.put("image", product.getImage());

        db.insert(DatabaseHelper.TABLE_PRODUCTS, null, values);

        db.close();
    }

    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_PRODUCTS, null);

        if (cursor.moveToFirst()) {

            do {

                Product product = new Product(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getDouble(3),
                        cursor.getString(4)
                );

                products.add(product);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return products;
    }
}
