package com.example.rma_namjestaj_n_m.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rma_namjestaj_n_m.R;
import com.example.rma_namjestaj_n_m.database.UserRepository;
import com.example.rma_namjestaj_n_m.models.User;
import com.example.rma_namjestaj_n_m.utils.SessionManager;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin;

    UserRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences prefs =
                getSharedPreferences(
                        "session",
                        MODE_PRIVATE
                );
        repository = new UserRepository(this);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {

            User user = repository.login(
                    etEmail.getText().toString(),
                    etPassword.getText().toString()
            );

            if(user != null) {
                Toast.makeText(
                        this,
                        "Uspješan login : "+user.getFirstName(),
                        Toast.LENGTH_LONG
                ).show();
                SessionManager.login(
                        user.getId(),
                        prefs);
                Log.d(
                        "LOGIN_TEST",
                        "After save: "
                                + user.getFirstName()

                );
                Toast.makeText(this, user.getFirstName(),   Toast.LENGTH_LONG).show();

                Intent intent =
                        new Intent(
                                LoginActivity.this,
                                ProductActivity.class
                        );
                intent.putExtra("loggedUser", user);
                startActivity(intent);
                finish();

            } else {

                Toast.makeText(
                        this,
                        "Pogrešan login",
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }
}