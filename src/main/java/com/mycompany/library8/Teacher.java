package com.mycompany.library8;

import java.util.List;

public class Teacher extends User {

    public Teacher(String id, String documentNumber, String documentType, String name, String password) {
        super(id, documentNumber, documentType, name, password);
    }

    public void reserveMaterial(Material material) {
        material.reserve();
    }

    public void returnMaterial(Material material) {
        material.returnMaterial();
    }

    public void renewMaterial(Material material) {
        material.renew();
    }

    public void viewAllUsers(List<User> users) {
        System.out.println("\n--- All Users ---");
        for (User user : users) {
            System.out.println("ID: " + user.id +
                               ", Name: " + user.name +
                               ", Document Number: " + user.documentNumber +
                               ", Document Type: " + user.documentType +
                               ", Password: " + user.password);
        }
    }
}
///tc