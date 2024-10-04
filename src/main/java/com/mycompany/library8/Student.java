package com.mycompany.library8;

public class Student extends User {

    public Student(String id, String documentNumber, String documentType, String name, String password) {
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
}
//st
