package com.bank.main;

import com.bank.dao.CustomerDAO;
import com.bank.model.Customer;
import com.bank.service.BankService;

import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final CustomerDAO cdao = new CustomerDAO();
    private static final BankService service = new BankService();

    public static void main(String[] args) {
        startApp();
    }

    private static void startApp() {
        while (true) {
            printMainMenu();
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    exitApp();
                    break;
                default:
                    System.out.println("Invalid choice ❌");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n==== BANK SYSTEM ====");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Enter choice: ");
    }

    private static void printUserMenu() {
        System.out.println("\n==== USER MENU ====");
        System.out.println("1. Deposit");
        System.out.println("2. Check Balance");
        System.out.println("3. Withdraw");
        System.out.println("4. Transaction History");
        System.out.println("5. Logout");
        System.out.print("Enter choice: ");
    }

    private static int getIntInput() {
        while (!sc.hasNextInt()) {
            System.out.println("Enter valid number ❌");
            sc.next();
        }
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    private static double getDoubleInput() {
        while (!sc.hasNextDouble()) {
            System.out.println("Enter valid amount ❌");
            sc.next();
        }
        double value = sc.nextDouble();
        sc.nextLine();
        return value;
    }

    private static void registerUser() {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        Customer c = new Customer(name, email, password);

        if (cdao.register(c)) {
            System.out.println("Registration Successful ✅");
        } else {
            System.out.println("Registration Failed ❌");
        }
    }

    private static void loginUser() {
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        Customer user = cdao.login(email, password);

        if (user != null) {
            System.out.println("Login Successful ✅");
            userDashboard(user);
        } else {
            System.out.println("Invalid Credentials ❌");
        }
    }

    private static void userDashboard(Customer user) {
        while (true) {
            printUserMenu();
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    deposit(user);
                    break;
                case 2:
                    checkBalance(user);
                    break;
                case 3:
                    withdraw(user);
                    break;
                case 4:
                    showTransactions(user);
                    break;
                case 5:
                    System.out.println("Logged out 👋");
                    return;
                default:
                    System.out.println("Invalid choice ❌");
            }
        }
    }

    private static void deposit(Customer user) {
        System.out.print("Enter amount: ");
        double amount = getDoubleInput();
        service.depositMoney(user.getId(), amount);
    }

    private static void withdraw(Customer user) {
        System.out.print("Enter amount: ");
        double amount = getDoubleInput();
        service.withdrawMoney(user.getId(), amount);
    }

    private static void checkBalance(Customer user) {
        double balance = cdao.getBalance(user.getId());
        System.out.println("Current Balance: " + balance);
    }

    private static void showTransactions(Customer user) {
        service.showTransactions(user.getId());
    }

    private static void exitApp() {
        System.out.println("Thank you! 👋");
        System.exit(0);
    }
}