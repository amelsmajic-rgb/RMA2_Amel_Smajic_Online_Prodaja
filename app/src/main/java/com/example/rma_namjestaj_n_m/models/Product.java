package com.example.rma_namjestaj_n_m.models;

import java.util.StringJoiner;

public class Product {

    private int id;
    private String name;
    private String description;
    private double price;
    private String image;

    public Product(int id,
                   String name,
                   String description,
                   double price,
                   String image) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}
