package com.example.rma_namjestaj_n_m.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rma_namjestaj_n_m.models.User;

public class UserRepository {

    private DatabaseHelper dbHelper;

    public UserRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public boolean registerUser(User user) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("firstName", user.getFirstName());
        values.put("lastName", user.getLastName());
        values.put("email", user.getEmail());
        values.put("address", user.getAddress());
        values.put("password", user.getPassword());
        values.put("phone", user.getPhone());
        values.put("isAdmin", user.getisAdmin());
        values.put("isSuperAdmin", user.getisSuperAdmin());

        long result = db.insert(DatabaseHelper.TABLE_USERS, null, values);

        db.close();

        return result != -1;
    }

    public User login(String email, String password) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + DatabaseHelper.TABLE_USERS + " WHERE email=? AND password=?",
                new String[]{email, password}
        );

        if(cursor.moveToFirst()) {

            User user = new User(
                    cursor.getInt(0),   // id
                    cursor.getInt(1),   // isAdmin
                    cursor.getInt(2),   // isSuperAdmin
                    cursor.getString(3),// firstName
                    cursor.getString(4),// lastName
                    cursor.getString(5),// email
                    cursor.getString(6),// address
                    cursor.getString(7),// password
                    cursor.getString(8) // phone
            );

            cursor.close();
            db.close();

            return user;
        }

        cursor.close();
        db.close();

        return null;
    }
}