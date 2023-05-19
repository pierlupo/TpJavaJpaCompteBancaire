package org.example.controller;

import org.example.dao.AccountDAO;
import org.example.dao.BankDAO;
import org.example.dao.CustomerDAO;
import org.example.entity.Account;
import org.example.entity.Customer;
import org.example.impl.AccountDAOImpl;
import org.example.impl.BankDAOImpl;
import org.example.impl.CustomerDAOImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class IHM {

    static Scanner scanner;
    String choice;
    String customerChoice;

    public IHM() {
        scanner = new Scanner(System.in);
    }
    private static EntityManagerFactory entityManagerFactory;
    private static AccountDAOImpl accountDAO;
    private static CustomerDAOImpl customerDAO;
    private static BankDAOImpl bankDAO;
    public void start() {
        entityManagerFactory = Persistence.createEntityManagerFactory("exo_jpa");
        customerDAO = new CustomerDAOImpl(entityManagerFactory);
        accountDAO = new AccountDAOImpl(entityManagerFactory);
        bankDAO = new BankDAOImpl(entityManagerFactory);
        do {
            menu();
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    customerMenu();
                    break;
                case "2":
                    accountMenu();
                    break;
//                case "3":
//                    bankMenu();
//                    break;
            }
        }while (!choice.equals("0"));
    }
    private void menu() {
        System.out.println("-------------------------------");
        System.out.println("TP Compte Bancaire JPA");
        System.out.println("-------------------------------");
        System.out.println("***************************************");
        System.out.println("Choose an option :");
        System.out.println("***************************************");
        System.out.println("1 - Customer menu ");
        System.out.println("2 - Account menu ");
        System.out.println("3 - Bank menu ");
        System.out.println("0 - Quit ");
    }

    // 1 - CRUD Customer
    private void customerMenu() {
        do {
            menuCustomer();
            customerChoice = scanner.nextLine();
            switch (customerChoice) {
                case "1":
                    addCustomer();
                    break;
                case "2":
                    getAllCustomers();
                    break;
                case "3":
                    deleteCustomer();
                    break;
                case "4":
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (!customerChoice.equals("4"));
    }

    //  Sous Menu Customer
    private void menuCustomer() {
        System.out.println("-------------------------------");
        System.out.println(" TP Compte Bancaire JPA");
        System.out.println("-------------------------------");
        System.out.println("***************************************");
        System.out.println("Choose an option please :");
        System.out.println("***************************************");
        System.out.println("1 - Add a customer");
        System.out.println("2 - list all customers");
        System.out.println("3 - Delete a customer");
        System.out.println("4 - go back");
        System.out.println("***************************************");
    }

    // 1 - Create customer
    private void addCustomer() {
        System.out.println("Enter your lastname : ");
        String lastname = scanner.nextLine();
        System.out.println("Enter your firstname : ");
        String firstname = scanner.nextLine();
        System.out.println("Enter your birthdate : (dd.MM.yyyy)");
        String birthdateStr = scanner.nextLine();
        LocalDate birthdate = LocalDate.parse(birthdateStr, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        Customer customer = new Customer(lastname,firstname,birthdate);
        if(customerDAO.addCustomer(customer)){
            System.out.println("Customer successfully added !");
        }else {
            System.out.println("Error while trying to add a customer ");
        }
    }

    // 2 - list all customers
    private void getAllCustomers() {
        System.out.println("List of all customers :");
        List<Customer> customers = customerDAO.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No Todos Found.");
        } else {
            System.out.println("### List of Users ###");
            for (Customer customer : customers) {
                System.out.println("############");
                System.out.println(customer.getId() + ". " + customer.getLastName()+", "+ customer.getLastName());
                System.out.println("############");
            }
        }
    }


    // 3 - Delete a customer
    private void deleteCustomer() {
        System.out.println("Enter the id of the customer to delete");
        Long customerId = scanner.nextLong();
        scanner.nextLine();
        if (customerDAO.deleteCustomer(customerId)) {
            System.out.println("Customer successfully deleted");
        } else {
            System.out.println("Error while deleting customer");
        }
    }

    // Crud Account
    private void accountMenu() {
        do {
            menuAccount();
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addAccount();
                    break;
                case "2":
                    getAllAccounts();
                    break;
                case "3":
                    deleteAccount();
                    break;
                case "4":
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (!choice.equals("4"));
    }

    //  Sous Menu Account
    private void menuAccount() {
        System.out.println("-------------------------------");
        System.out.println(" TP Compte Bancaire JPA");
        System.out.println("-------------------------------");
        System.out.println("***************************************");
        System.out.println("Choose an option :");
        System.out.println("***************************************");
        System.out.println("1 - Add an account");
        System.out.println("2 - List of all accounts");
        System.out.println("3 - Delete an event");
        System.out.println("4 - Go Back");
        System.out.println("***************************************");
    }

    // 1 - Add an account
    private void addAccount() {
        System.out.println("Enter the libelle of the account :");
        String libelle = scanner.nextLine();
        System.out.println("Enter the IBAN of the account :");
        String IBAN = scanner.nextLine();
        System.out.println("Enter the sold : ");
        Double sold = scanner.nextDouble();
        Account account = new Account(libelle,IBAN,sold);
        if(accountDAO.addAccount(account)){
            System.out.println("Customer successfully added !");
        }else {
            System.out.println("Error while trying to add a customer ");
        }

    }

    // 2 - List of all accounts
    private void getAllAccounts() {
        System.out.println("List of all accounts :");
        List<Account> accounts = accountDAO.getAllAccounts();
        if (accounts.isEmpty()) {
            System.out.println("No accounts Found.");
        } else {
            System.out.println("### List of Accounts ###");
            for (Account account : accounts) {
                System.out.println("############");
                System.out.println(account.getId() + ". " + account.getIBAN()+", "+ account.getSolde());
                System.out.println("############");
            }
        }
    }

    // 3 - Delete an account
    private void deleteAccount() {
        System.out.println("List of accounts :");
        getAllAccounts();
        System.out.println("Enter the id of the account to delete");
        Long accountId = scanner.nextLong();
        scanner.nextLine();
        if (accountDAO.deleteAccount(accountId)) {
            System.out.println("Account successfully deleted");
        } else {
            System.out.println("Error while deleting account");
        }
    }


}
