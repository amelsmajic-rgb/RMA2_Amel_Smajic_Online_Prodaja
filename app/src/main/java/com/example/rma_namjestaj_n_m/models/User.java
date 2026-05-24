package com.example.rma_namjestaj_n_m.models;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String password;
    private String phone;
    private int isAdmin, isSuperAdmin;

    public User(
            int id,
            int isAdmin,
            int isSuperAdmin,
            String firstName,
            String lastName,
            String email,
            String address,
            String password,
            String phone
    ) {
        this.id = id;
        this.isAdmin = isAdmin;
        this.isSuperAdmin = isSuperAdmin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.password = password;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public int getisAdmin() {
        return isAdmin;
    }

    public int getisSuperAdmin() {
        return isSuperAdmin;
    }

    public void setFirstName(String x) {
        firstName = x;
    }

    public void setLastName(String x) {
        lastName = x;
    }

    public void setEmail(String x) {
        email = x;
    }

    public void setAddress(String x) {
        address = x;
    }

    public void setPassword(String x) {
        password = x;
    }

    public void setPhone(String x) {
        phone = x;
    }

    public void setAdmin(int admin) {
        isAdmin = admin;
    }

    public void setSuperAdmin(int superAdmin) {
        isSuperAdmin = superAdmin;
    }
}
