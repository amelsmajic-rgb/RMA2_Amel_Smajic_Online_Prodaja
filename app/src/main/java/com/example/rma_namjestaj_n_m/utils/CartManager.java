package com.example.rma_namjestaj_n_m.utils;

import com.example.rma_namjestaj_n_m.models.Product;

import java.util.ArrayList;
import java.util.List;

public class CartManager {

    static List<Product> cartItems =
            new ArrayList<>();

    public static void addToCart(Product product) {

        cartItems.add(product);
    }

    public static List<Product> getCartItems() {

        return cartItems;
    }

    public static double getTotalPrice() {

        double total = 0;

        for(Product product : cartItems) {

            total += product.getPrice();
        }

        return total;
    }

    public static int getCartCount() {

        return cartItems.size();
    }

    public static void clearCart() {

        cartItems.clear();
    }
}