package com.example.myapplication.model;

import java.io.Serializable;

public class Item implements Serializable {
    private int id;
    private String name, ct, date, status;
    private int countries;

    public Item() {
    }

    public Item(int id, String name, String ct, String date, String status, int countries) {
        this.id = id;
        this.name = name;
        this.ct = ct;
        this.date = date;
        this.status = status;
        this.countries = countries;
    }

    public Item(String name, String ct, String date, String status, int countries) {
        this.name = name;
        this.ct = ct;
        this.date = date;
        this.status = status;
        this.countries = countries;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCountries() {
        return countries;
    }

    public void setCountries(int countries) {
        this.countries = countries;
    }
}
