package com.example.rmsi.sqlliteapp;

/**
 * Created by Amitabh.Basu on 17-07-2015.
 */
public class Product {
    private int _id;
    private String _productName;

    public Product() {
    }

    public Product(String productName) {
        this._productName = productName;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_productName(String _productName) {
        this._productName = _productName;
    }

    public int get_id() {
        return _id;
    }

    public String get_productName() {
        return _productName;
    }
}