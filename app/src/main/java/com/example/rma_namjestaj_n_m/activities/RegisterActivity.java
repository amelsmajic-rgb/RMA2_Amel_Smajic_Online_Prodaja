package com.example.rma_namjestaj_n_m.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rma_namjestaj_n_m.R;
import com.example.rma_namjestaj_n_m.database.UserRepository;
import com.example.rma_namjestaj_n_m.models.User;

public class RegisterActivity extends AppCompatActivity {

    EditText etFirstName, etLastName, etEmail,
            etAddress, etPassword, etPhone;

    Button btnRegister;

    UserRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        repository = new UserRepository(this);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etEmail = findViewById(R.id.etEmail);
        etAddress = findViewById(R.id.etAddress);
        etPassword = findViewById(R.id.etPassword);
        etPhone = findViewById(R.id.etPhone);

        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {

            User user = new User(
                    0, // id
                    0, // isAdmin
                    0, // isSuperAdmin
                    etFirstName.getText().toString(),
                    etLastName.getText().toString(),
                    etEmail.getText().toString(),
                    etAddress.getText().toString(),
                    etPassword.getText().toString(),
                    etPhone.getText().toString()
            );

            boolean success = repository.registerUser(user);

            if(success) {
                Toast.makeText(this,
                        "Registracija uspješna",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}