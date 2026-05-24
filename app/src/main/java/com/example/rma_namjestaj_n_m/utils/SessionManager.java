package com.example.rma_namjestaj_n_m.utils;

import android.content.SharedPreferences;

import com.example.rma_namjestaj_n_m.models.User;

public class SessionManager {

    private static int loggedUserId;
    private static Object prefs;

    public static void login(int id, SharedPreferences prefs) {
        loggedUserId = id;
        prefs.edit()
                .putInt("loggedUserId", id)
                .apply();
    }
    public static int getLoggedUserId(
            SharedPreferences prefs
    ) {

        return prefs.getInt(
                "loggedUserId",
                -1
        );
    }
    public static int getLoggedUser() {
        return loggedUserId;
    }

    public static boolean isLoggedIn() {
        return loggedUserId != -1;
    }

    public static void logout() {
        loggedUserId = -1;
    }


}