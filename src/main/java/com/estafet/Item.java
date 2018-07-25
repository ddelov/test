package com.estafet;

import java.io.Serializable;

/**
 * Created by Delcho Delov on 23.07.18.
 */
public class Item implements Serializable{
    private int id;
    private String product;
    private float price;

    public Item(int id, String product, float price) {
        this.id = id;
        this.product = product;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
