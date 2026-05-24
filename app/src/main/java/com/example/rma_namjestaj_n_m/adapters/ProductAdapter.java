package com.example.rma_namjestaj_n_m.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rma_namjestaj_n_m.R;
import com.example.rma_namjestaj_n_m.models.Product;
import com.example.rma_namjestaj_n_m.utils.CartManager;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;

    TextView txtCartCount;

    public ProductAdapter(
            List<Product> productList,
            TextView txtCartCount) {

        this.productList = productList;
        this.txtCartCount = txtCartCount;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ProductViewHolder holder,
            int position) {

        Product product = productList.get(position);

        holder.name.setText(product.getName());
        holder.description.setText(product.getDescription());
        holder.price.setText(product.getPrice() + " KM");

        String imagePath = product.getImage();
        if (imagePath != null && !imagePath.isEmpty()) {
            try {
                holder.image.setImageURI(Uri.parse(imagePath));
            } catch (SecurityException e) {
                // If permission is denied (common on MIUI), show a placeholder
                holder.image.setImageResource(android.R.drawable.ic_menu_gallery);
                e.printStackTrace();
            }
        } else {
            holder.image.setImageResource(android.R.drawable.ic_menu_gallery);
        }

        holder.btnAdd.setOnClickListener(v -> {

            CartManager.addToCart(product);

            txtCartCount.setText(
                    CartManager.getCartCount()
                            + " items"
            );
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name, description, price;
        Button btnAdd;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imgProduct);
            name = itemView.findViewById(R.id.txtName);
            description = itemView.findViewById(R.id.txtDescription);
            price = itemView.findViewById(R.id.txtPrice);
            btnAdd = itemView.findViewById(R.id.btnAdd);
        }
    }
}