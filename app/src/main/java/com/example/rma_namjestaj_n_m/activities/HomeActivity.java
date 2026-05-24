package com.example.rma_namjestaj_n_m.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rma_namjestaj_n_m.R;
import com.example.rma_namjestaj_n_m.utils.SessionManager;

public class HomeActivity extends AppCompatActivity {

    Button btnLoginPage;
    Button btnRegisterPage;
    Button btnProductActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnLoginPage = findViewById(R.id.btnLoginPage);
        btnRegisterPage = findViewById(R.id.btnRegisterPage);
        btnProductActivity = findViewById(R.id.btnProductList);
        btnLoginPage.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            HomeActivity.this,
                            LoginActivity.class
                    );

            startActivity(intent);
        });

        btnRegisterPage.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            HomeActivity.this,
                            RegisterActivity.class
                    );

            startActivity(intent);
        });

        btnProductActivity.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            HomeActivity.this,
                            ProductActivity.class
                    );

            startActivity(intent);
        });
    }
}