package com.example.rma_namjestaj_n_m.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rma_namjestaj_n_m.R;
import com.example.rma_namjestaj_n_m.models.Order;

import java.util.List;

public class OrderAdapter
        extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    List<Order> orders;

    public OrderAdapter(List<Order> orders) {

        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(
                                R.layout.item_order,
                                parent,
                                false
                        );

        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull OrderViewHolder holder,
            int position) {

        Order order = orders.get(position);

        holder.txtDate.setText(
                order.getOrderDate()
        );

        holder.txtTotal.setText(
                order.getTotalPrice()
                        + " KM"
        );
    }

    @Override
    public int getItemCount() {

        return orders.size();
    }

    static class OrderViewHolder
            extends RecyclerView.ViewHolder {

        TextView txtDate;
        TextView txtTotal;

        public OrderViewHolder(
                @NonNull View itemView) {

            super(itemView);

            txtDate =
                    itemView.findViewById(R.id.txtOrderDate);

            txtTotal =
                    itemView.findViewById(R.id.txtOrderTotal);
        }
    }
}