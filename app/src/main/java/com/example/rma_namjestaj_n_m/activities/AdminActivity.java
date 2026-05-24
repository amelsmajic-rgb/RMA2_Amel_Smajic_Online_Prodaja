package com.example.rma_namjestaj_n_m.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rma_namjestaj_n_m.R;

public class AdminActivity
        extends AppCompatActivity {

    Button btnAddProduct;

    Button btnUsers;

    @Override
    protected void onCreate(
            Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_admin
        );

        btnAddProduct =
                findViewById(R.id.btnAddProduct);

        btnUsers =
                findViewById(R.id.btnUsers);

        btnAddProduct.setOnClickListener(v -> {

            startActivity(
                    new Intent(
                            this,
                            CreateProductActivity.class
                    )
            );
        });

        btnUsers.setOnClickListener(v -> {

            startActivity(
                    new Intent(
                            this,
                            UsersActivity.class
                    )
            );
        });
    }
}