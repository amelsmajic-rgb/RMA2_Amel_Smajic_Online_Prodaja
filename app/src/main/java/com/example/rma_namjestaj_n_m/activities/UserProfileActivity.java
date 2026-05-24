package com.example.rma_namjestaj_n_m.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rma_namjestaj_n_m.R;
import com.example.rma_namjestaj_n_m.adapters.OrderAdapter;
import com.example.rma_namjestaj_n_m.database.OrderRepository;
import com.example.rma_namjestaj_n_m.models.User;
import com.example.rma_namjestaj_n_m.utils.SessionManager;

public class UserProfileActivity
        extends AppCompatActivity {

    ImageView imgWallpaper;
    ImageView imgProfile;

    TextView txtUserName;
    TextView txtEmail;

    Button btnEditProfile;
    Button btnAdminPanel;
    RecyclerView recyclerOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast .makeText(this,"User activity started",Toast.LENGTH_LONG).show();
        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_user_profile
        );
        btnAdminPanel = findViewById(R.id.btnAdminPanel);

        imgWallpaper =
                findViewById(R.id.imgWallpaper);

        imgProfile =
                findViewById(R.id.imgProfile);

        txtUserName =
                findViewById(R.id.txtUserName);

        txtEmail =
                findViewById(R.id.txtEmail);

        btnEditProfile =
                findViewById(R.id.btnEditProfile);

        recyclerOrders =
                findViewById(R.id.recyclerOrders);
        User user =
                (User) getIntent()
                        .getSerializableExtra(
                                "loggedUser"
                        );
        if(user!=null) {

            if(user.getisAdmin()==0){ btnAdminPanel.setVisibility(View.VISIBLE);
                btnAdminPanel.setOnClickListener(v -> {

                    startActivity(
                            new Intent(
                                    UserProfileActivity.this,
                                    AdminActivity.class
                            )
                    );
                });
            }
            Toast.makeText(
                    this,
                    "Welcome " + user.getFirstName(),
                    Toast.LENGTH_LONG
            ).show();
        txtUserName.setText(
                user.getFirstName()
                        + " "
                        + user.getLastName()
        );

        txtEmail.setText(
                user.getEmail()
        );

        recyclerOrders.setLayoutManager(
                new LinearLayoutManager(this)
        );

        OrderRepository repository =
                new OrderRepository(this);


        OrderAdapter adapter =
                new OrderAdapter(
                        repository.getUserOrders(
                                user.getId()
                        )

                );

        recyclerOrders.setAdapter(adapter);
    }else {

            Toast.makeText(
                    this,
                    "User is NULL",
                    Toast.LENGTH_LONG
            ).show();}

    }
}