package com.example.rma_namjestaj_n_m.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rma_namjestaj_n_m.R;
import com.example.rma_namjestaj_n_m.adapters.CartAdapter;
import com.example.rma_namjestaj_n_m.database.OrderRepository;
import com.example.rma_namjestaj_n_m.models.Order;
import com.example.rma_namjestaj_n_m.utils.CartManager;
import com.example.rma_namjestaj_n_m.utils.SessionManager;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerCart;

    TextView txtTotal;

    Button btnCheckout;

    CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cart);

        recyclerCart =
                findViewById(R.id.recyclerCart);

        txtTotal =
                findViewById(R.id.txtTotal);

        btnCheckout =
                findViewById(R.id.btnCheckout);

        recyclerCart.setLayoutManager(
                new LinearLayoutManager(this)
        );

        adapter = new CartAdapter(
                CartManager.getCartItems()
        );

        recyclerCart.setAdapter(adapter);

        txtTotal.setText(
                "Total: "
                        + CartManager.getTotalPrice()
                        + " KM"
        );

        btnCheckout.setOnClickListener(v -> {

            OrderRepository repository =
                    new OrderRepository(this);

            String currentDate = new java.text.SimpleDateFormat("dd.MM.yyyy", java.util.Locale.getDefault()).format(new java.util.Date());
            Order order = new Order(
                    0,
                    SessionManager
                            .getLoggedUser(),
                    CartManager.getTotalPrice(),
                    currentDate
            );

            repository.insertOrder(order);

            Toast.makeText(
                    this,
                    "Order placed!",
                    Toast.LENGTH_LONG
            ).show();

            CartManager.clearCart();

            finish();
        });
    }
}