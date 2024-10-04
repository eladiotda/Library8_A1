package com.mycompany.library8;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private List<User> users;
    private List<Material> materials;
    private Scanner scanner;

    public Menu() {
        users = new ArrayList<>();
        materials = new ArrayList<>();
        scanner = new Scanner(System.in);
        preloadData(); // Precarga de datos al iniciar el menú
    }

    // logg
    public void start() {
        boolean exitProgram = false;
        while (!exitProgram) {
            User currentUser = login();

            if (currentUser != null) {
                exitProgram = displayMenu(currentUser);
            }
        }
        System.out.println("Program has ended.");
    }

    // datos
    private void preloadData() {
        materials.add(new Material("001", "Book", "Introduction to Java", "John Doe", 5));
        materials.add(new Material("002", "Magazine", "Tech Today", "Jane Smith", 3));
        materials.add(new Material("003", "Digital", "Cloud Computing Basics", "Alan Turing", 10));
        materials.add(new Material("004", "Book", "Data Structures", "Ada Lovelace", 2));

        users.add(new Admin("1", "12345", "ID", "Alice", "pass123"));
        users.add(new Teacher("2", "67890", "ID", "Bob", "pass456"));
        users.add(new Student("3", "11223", "ID", "Charlie", "pass789"));
        users.add(new Student("4", "44556", "ID", "Dave", "pass101"));
    }

    // Inicio de sesión
    private User login() {
        int attempts = 3; // Número de intentos

        while (attempts > 0) {
            System.out.println("Welcome to the Library System. Attempts left: " + attempts);
            System.out.print("Enter your document number: ");
            String documentNumber = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            for (User user : users) {
                if (user.documentNumber.equals(documentNumber) && user.password.equals(password)) {
                    System.out.println("Login successful! Welcome, " + user.name);
                    return user;
                }
            }

            attempts--;
            if (attempts > 0) {
                System.out.println("Invalid credentials. Try again.");
            } else {
                System.out.println("Too many failed attempts. Exiting...");
            }
        }

        return null;  // 3 intent
    }

    // rol user
    private boolean displayMenu(User currentUser) {
        boolean exitMenu = false;
        while (!exitMenu) {
            System.out.println("\n--- Library Menu ---");
            if (currentUser instanceof Admin) {
                System.out.println("1. View all materials");
                System.out.println("2. View all users");
                System.out.println("3. Add material");
                System.out.println("4. Modify material");
                System.out.println("5. Add user");
                System.out.println("6. Modify user");
                System.out.println("7. Reserve material");
                System.out.println("8. Return material");
                System.out.println("9. Renew material");
                System.out.println("10. Exit");
            } else if (currentUser instanceof Teacher) {
                System.out.println("1. View all materials");
                System.out.println("2. View all users");
                System.out.println("3. Reserve material");
                System.out.println("4. Return material");
                System.out.println("5. Renew material");
                System.out.println("6. Exit");
            } else if (currentUser instanceof Student) {
                System.out.println("1. View all materials");
                System.out.println("2. Reserve material");
                System.out.println("3. Return material");
                System.out.println("4. Renew material");
                System.out.println("5. Exit");
            }

            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    currentUser.viewMaterials(materials);
                    break;
                case 2:
                    if (currentUser instanceof Admin) {
                        ((Admin) currentUser).viewAllUsers(users);
                    } else if (currentUser instanceof Teacher) {
                        ((Teacher) currentUser).viewAllUsers(users);
                    } else {
                        reserveMaterial(currentUser);
                    }
                    break;
                case 3:
                    if (currentUser instanceof Admin) {
                        ((Admin) currentUser).addMaterial(materials);
                    } else if (currentUser instanceof Teacher || currentUser instanceof Student) {
                        reserveMaterial(currentUser);
                    }
                    break;
                case 4:
                    if (currentUser instanceof Admin) {
                        modifyMaterial();
                    } else {
                        returnMaterial(currentUser);
                    }
                    break;
                case 5:
                    if (currentUser instanceof Admin) {
                        ((Admin) currentUser).addUser(users);
                    } else {
                        renewMaterial(currentUser);
                    }
                    break;
                case 6:
                    if (currentUser instanceof Admin) {
                        modifyUser();
                    } else if (currentUser instanceof Teacher || currentUser instanceof Student) {
                        exitMenu = true;
                    }
                    break;
                case 7:
                    if (currentUser instanceof Admin) {
                        reserveMaterial(currentUser);
                    }
                    break;
                case 8:
                    if (currentUser instanceof Admin) {
                        returnMaterial(currentUser);
                    }
                    break;
                case 9:
                    if (currentUser instanceof Admin) {
                        renewMaterial(currentUser);
                    }
                    break;
                case 10:
                    exitMenu = true;
                    break;
                default:
                    System.out.println("Invlid option Please try again.");
            }
        }

        System.out.println("Loging out...");
        return false;  // retur
    }

    
    private void modifyMaterial() {
        System.out.print("Ente material code to modif: ");
        String code = scanner.nextLine();

        for (Material material : materials) {
            if (material.code.equals(code)) {
                System.out.print("Enter new name: ");
                material.name = scanner.nextLine();
                System.out.print("Enter  type: ");
                material.type = scanner.nextLine();
                System.out.print("Enter new author: ");
                material.author = scanner.nextLine();
                System.out.print("Enter new quantity: ");
                material.quantity = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Material updated.");
                return;
            }
        }
        System.out.println("Materia not found.");
    }

    private void modifyUser() {
        System.out.print("Enter user ID to modify: ");
        String id = scanner.nextLine();

        for (User user : users) {
            if (user.id.equals(id)) {
                System.out.print("Enter new name: ");
                user.name = scanner.nextLine();
                System.out.print("Enter new document number: ");
                user.documentNumber = scanner.nextLine();
                System.out.print("Enter new password: ");
                user.password = scanner.nextLine();
                System.out.println("User updated.");
                return;
            }
        }
        System.out.println("User not found.");
    }

    private void reserveMaterial(User user) {
        System.out.print("Enter material code to reserve: ");
        String code = scanner.nextLine();

        for (Material material : materials) {
            if (material.code.equals(code)) {
                material.reserve();
                return;
            }
        }
        System.out.println("Material not found.");
    }

    private void returnMaterial(User user) {
        System.out.print("Enter material code to return: ");
        String code = scanner.nextLine();

        for (Material material : materials) {
            if (material.code.equals(code)) {
                material.returnMaterial();
                return;
            }
        }
        System.out.println("Material not found.");
    }

    private void renewMaterial(User user) {
        System.out.print("Enter material code to renew: ");
        String code = scanner.nextLine();

        for (Material material : materials) {
            if (material.code.equals(code)) {
                material.renew();
                return;
            }
        }
        System.out.println("Material not found.");
    }
}
