package com.example.rma_namjestaj_n_m.activities;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rma_namjestaj_n_m.R;
import com.example.rma_namjestaj_n_m.adapters.ProductAdapter;
import com.example.rma_namjestaj_n_m.database.ProductRepository;
import com.example.rma_namjestaj_n_m.models.Product;
import com.example.rma_namjestaj_n_m.utils.CartManager;

import java.util.ArrayList;
import java.util.List;

import android.widget.Toast;

import com.example.rma_namjestaj_n_m.models.User;
import com.example.rma_namjestaj_n_m.utils.SessionManager;
public class ProductActivity extends AppCompatActivity {
    ImageView imgUser;

    TextView txtUserName;
    RecyclerView recyclerProducts;
    Button btnOpenCart;
    ProductAdapter adapter;

    List<Product> productList;
    ImageView imgCart;

    TextView txtCartCount;
    SharedPreferences prefs;
    int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_product);

        prefs =
                getSharedPreferences(
                        "session",
                        MODE_PRIVATE
                );
        userId =
                SessionManager.getLoggedUserId(
                        prefs
                );

        imgUser =
                findViewById(R.id.imgUser);

        txtUserName =
                findViewById(R.id.txtUserName);

        recyclerProducts =
                findViewById(R.id.recyclerProducts);

        btnOpenCart =
                findViewById(R.id.btnOpenCart);

        imgCart =
                findViewById(R.id.imgCart);

        txtCartCount =
                findViewById(R.id.txtCartCount);

        recyclerProducts.setLayoutManager(
                new LinearLayoutManager(this)
        );

        // SESSION CHECK
        User user =
                (User) getIntent()
                        .getSerializableExtra(
                                "loggedUser"
                        );
        if(user!= null) {


            SessionManager.login(user.getId(), prefs);
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

        } else {

            Toast.makeText(
                    this,
                    "User is NULL",
                    Toast.LENGTH_LONG
            ).show();
        }

        // CART CLICK

        imgCart.setOnClickListener(v -> {

            startActivity(
                    new Intent(
                            ProductActivity.this,
                            CartActivity.class
                    )
            );
        });

        // USER CLICK

        imgUser.setOnClickListener(v -> {
            Toast.makeText(
                    this,
                    String.valueOf(
                          user
                    ),
                    Toast.LENGTH_LONG
            ).show();
            Intent intent =
                    new Intent(
                            ProductActivity.this,
                            UserProfileActivity.class
                    );

            intent.putExtra(
                    "loggedUser",
                    user
            );

            startActivity(intent);
        });

        // PRODUCTS

        ProductRepository repository = new ProductRepository(this);

        productList = repository.getAllProducts();

        adapter = new ProductAdapter(
                productList,
                txtCartCount
        );

        recyclerProducts.setAdapter(adapter);

        btnOpenCart.setOnClickListener(v -> {

            startActivity(
                    new Intent(
                            ProductActivity.this,
                            CartActivity.class
                    )
            );
        });
    }
    @Override
    protected void onResume() {

        super.onResume();

        txtCartCount.setText(
                CartManager.getCartCount()
                        + " items"
        );
    }
}