package com.mycompany.library8;

import java.util.List;
import java.util.Scanner;

public class Admin extends User {

    public Admin(String id, String documentNumber, String documentType, String name, String password) {
        super(id, documentNumber, documentType, name, password);
    }

    public void addUser(List<User> users) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter user ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter document number: ");
        String documentNumber = scanner.nextLine();
        System.out.print("Enter document type: ");
        String documentType = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // user type
        System.out.println("Select use type: ");
        System.out.println("1. Admin");
        System.out.println("2. Teacher");
        System.out.println("3. Student");
        int userType = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        User newUser = null;

        switch (userType) {
            case 1:
                newUser = new Admin(id, documentNumber, documentType, name, password);
                break;
            case 2:
                newUser = new Teacher(id, documentNumber, documentType, name, password);
                break;
            case 3:
                newUser = new Student(id, documentNumber, documentType, name, password);
                break;
            default:
                System.out.println("Invalid user type. Defaulting to Student.");
                newUser = new Student(id, documentNumber, documentType, name, password);
                break;
        }

        users.add(newUser);
        System.out.println("User added: " + name);
    }
//us
    public void modifyUser(List<User> users, String userId, User modifiedUser) {
        for (User user : users) {
            if (user.id.equals(userId)) {
                user.name = modifiedUser.name;
                user.documentNumber = modifiedUser.documentNumber;
                user.password = modifiedUser.password;
                System.out.println("User updated: " + user.name);
                return;
            }
        }
        System.out.println("User not found.");
    }

    public void addMaterial(List<Material> materials) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter material code: ");
        String code = scanner.nextLine();
        System.out.print("Enter material type: ");
        String type = scanner.nextLine();
        System.out.print("Enter material name: ");
        String name = scanner.nextLine();
        System.out.print("Enter material author: ");
        String author = scanner.nextLine();
        System.out.print("Enter material quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        Material newMaterial = new Material(code, type, name, author, quantity);
        materials.add(newMaterial);
        
        System.out.println("Material added successfully: " + name);
    }

    public void modifyMaterial(List<Material> materials, String materialCode, Material modifiedMaterial) {
        for (Material material : materials) {
            if (material.code.equals(materialCode)) {
                material.name = modifiedMaterial.name;
                material.type = modifiedMaterial.type;
                material.quantity = modifiedMaterial.quantity;
                System.out.println("Material updated: " + material.name);
                return;
            }
        }
        System.out.println("Material not found.");
    }

    public void viewAllUsers(List<User> users) {
        System.out.println("\n--- All Users ---");
        for (User user : users) {
            String userType = user instanceof Admin ? "Admin" : 
                              user instanceof Teacher ? "Teacher" :
                              user instanceof Student ? "Student" : "User";
            System.out.println("User Type: " + userType +
                               ", ID: " + user.id +
                               ", Name: " + user.name +
                               ", Document Number: " + user.documentNumber +
                               ", Document Type: " + user.documentType +
                               ", Password: " + user.password);
        }
    }
}
