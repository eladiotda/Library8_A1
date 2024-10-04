package com.mycompany.library8;

import java.util.List;

public class User {
    public String id;
    public String documentNumber;
    public String documentType;
    public String name;
    public String password;

    public User(String id, String documentNumber, String documentType, String name, String password) {
        this.id = id;
        this.documentNumber = documentNumber;
        this.documentType = documentType;
        this.name = name;
        this.password = password;
    }

    public void viewMaterials(List<Material> materials) {
        System.out.println("\n--- Available Materials ---");
        for (Material material : materials) {
            System.out.println("Code: " + material.code +
                               ", Name: " + material.name +
                               ", Type: " + material.type +
                               ", Author: " + material.author +
                               ", Quantity Available: " + material.quantity);
        }
    }
}
///is