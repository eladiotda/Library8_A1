package com.mycompany.library8;

public class Material {
    public String code;
    public String type;
    public String name;
    public String author;
    public int quantity;

    public Material(String code, String type, String name, String author, int quantity) {
        this.code = code;
        this.type = type;
        this.name = name;
        this.author = author;
        this.quantity = quantity;
    }

    public void reserve() {
        if (quantity > 0) {
            quantity--;
            System.out.println("Material successfully reserved. Remaining quantity: " + quantity);
        } else {
            System.out.println("No materials available for reservation.");
        }
    }

    public void returnMaterial() {
        quantity++;
        System.out.println("Material returned. Available quantity: " + quantity);
    }

    public void renew() {
        System.out.println("Material renewed for 7 more days.");
    }
}
//mt