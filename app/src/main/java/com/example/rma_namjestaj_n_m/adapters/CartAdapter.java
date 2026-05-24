package com.example.rma_namjestaj_n_m.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rma_namjestaj_n_m.R;
import com.example.rma_namjestaj_n_m.models.Product;

import java.util.List;

public class CartAdapter
        extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    List<Product> cartItems;

    public CartAdapter(List<Product> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.item_cart,
                        parent,
                        false
                );

        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull CartViewHolder holder,
            int position) {

        Product product = cartItems.get(position);

        holder.txtName.setText(product.getName());

        holder.txtPrice.setText(
                product.getPrice() + " KM"
        );
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class CartViewHolder
            extends RecyclerView.ViewHolder {

        TextView txtName, txtPrice;

        public CartViewHolder(@NonNull View itemView) {

            super(itemView);

            txtName =
                    itemView.findViewById(R.id.txtCartName);

            txtPrice =
                    itemView.findViewById(R.id.txtCartPrice);
        }
    }
}