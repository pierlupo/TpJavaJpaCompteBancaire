package org.example.controller;

import org.example.dao.AccountDAO;
import org.example.dao.BankDAO;
import org.example.dao.CustomerDAO;
import org.example.entity.Account;
import org.example.entity.Bank;
import org.example.entity.Customer;
import org.example.entity.Operation;
import org.example.impl.AccountDAOImpl;
import org.example.impl.BankDAOImpl;
import org.example.impl.CustomerDAOImpl;
import org.example.impl.OperationDAOImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.SQLException;
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
    private static OperationDAOImpl operationDAO;
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
                case "3":
                    bankMenu();
                    break;
                case "0":
                    System.out.println("See you later, bye bye!");
                    entityManagerFactory.close();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
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
            System.out.println("No Customers Found.");
        } else {
            System.out.println("### List of Customers ###");
            for (Customer customer : customers) {
                System.out.println("############");
                System.out.println(customer.getId() + ". " + customer.getLastName()+", "+ customer.getFirstName()+", "+customer.getBirthDate());
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
                    getAccountByCustomerId();
                    break;
                case "4":
                    deleteAccount();
                    break;
//                case "5":
//                    depositAction();
//                    break;
//                case "6":
//                    withdrawalAction();
//                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (!choice.equals("7"));
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
        System.out.println("3 - List of accounts by customer");
        System.out.println("4 - Delete an account");
        System.out.println("5 - Make a deposit");
        System.out.println("6 - Make a withdrawal");
        System.out.println("7 - Go Back");
        System.out.println("***************************************");
    }

    // 1 - Add an account
    private void addAccount() {
        System.out.println("Enter the libelle of the account :");
        String libelle = scanner.nextLine();
        System.out.println("Enter the IBAN of the account :");
        String IBAN = scanner.nextLine();
        System.out.println("Enter the amount : ");
        Double amount = scanner.nextDouble();
        System.out.println("Enter the id of the customer who will own this account : ");
        Long customerId = scanner.nextLong();
        System.out.println("Enter the id of the bank of the owner's account :  ");
        Long bankId = scanner.nextLong();
        Account account = new Account(libelle,IBAN,amount);
        if(accountDAO.addAccount(account, customerId, bankId)){
            System.out.println("Account successfully added !");
        }else {
            System.out.println("Error while trying to add an account ");
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
                System.out.println(account.getId() + ". " + account.getIBAN()+", "+ account.getAmount());
                System.out.println("############");
            }
        }
    }
    // 3 - List of accounts by customer
    private void getAccountByCustomerId(){
        System.out.println("List of accounts :");
        getAllAccounts();
        System.out.println("Please enter the id of the account of a customer : ");
        Long accountId = scanner.nextLong();
        scanner.nextLine();
        List<Account> accounts = accountDAO.getAccountByCustomerId(accountId);
        if (accounts.isEmpty()) {
            System.out.println("No accounts Found.");
        } else {
            System.out.println("### List of Accounts of the customer chosen : ###");
            for (Account account : accounts) {
                System.out.println("############");
                System.out.println(account.getId() + ". " + account.getIBAN()+", "+ account.getAmount());
                System.out.println("############");
            }
        }
    }


    // 4 - Delete an account
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

//    private void depositAction() {
//        Account account = getAccountByCustomerId();
//        System.out.print("Please enter the amount of the deposit : ");
//        double amount = scanner.nextDouble();
//        scanner.nextLine();
//        Operation operation = new Operation(amount, Math.toIntExact(account.getId()));
//        try {
//            if(account.makeDeposit(operation)) {
//                System.out.println("Deposit Ok");
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    private void withdrawalAction() {
//        Account account = getAccountByCustomerId();
//        System.out.print("Please enter the amount of the withdrawal : ");
//        double amount = scanner.nextDouble();
//        scanner.nextLine();
//        Operation operation = new Operation(amount*-1, Math.toIntExact(account.getId()));
//        try {
//            if(account.makeWithDrawal(operation)) {
//                System.out.println("Withdrawal Ok");
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
    // Crud Bank
    private void bankMenu() {
        do {
            menuBank();
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addBank();
                    break;
                case "2":
                    getAllBanks();
                    break;
                case "3":
                    deleteBank();
                    break;
                case "4":
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (!choice.equals("4"));
    }
    //  Sous Menu Bank
    private void menuBank() {
        System.out.println("-------------------------------");
        System.out.println(" TP Compte Bancaire JPA");
        System.out.println("-------------------------------");
        System.out.println("***************************************");
        System.out.println("Choose an option :");
        System.out.println("***************************************");
        System.out.println("1 - Add a bank");
        System.out.println("2 - List of all banks");
        System.out.println("3 - Delete a bank");
        System.out.println("4 - Go Back");
        System.out.println("***************************************");
    }

    // 1 - Add a bank
    private void addBank() {
        System.out.println("Enter the bank's name : ");
        String bankName = scanner.nextLine();
        System.out.println("Enter the address of the bank :");
        String address = scanner.nextLine();
        Bank bank = new Bank(bankName, address);
        if(bankDAO.addBank(bank)){
            System.out.println("Bank successfully added !");
        }else {
            System.out.println("Error while trying to add a bank ");
        }

    }

    // 2 - List of all banks
    private void getAllBanks() {
        System.out.println("List of all banks :");
        List<Bank> banks = bankDAO.getAllBanks();
        if (banks.isEmpty()) {
            System.out.println("No banks Found.");
        } else {
            System.out.println("### List of banks ###");
            for (Bank bank : banks) {
                System.out.println("############");
                System.out.println(bank.getId() + ". "+bank.getName()+", "+ bank.getAddress());
                System.out.println("############");
            }
        }
    }

    // 3 - Delete a Bank
    private void deleteBank() {
        System.out.println("List of banks :");
        getAllBanks();
        System.out.println("Enter the id of the bank to delete");
        Long bankId = scanner.nextLong();
        scanner.nextLine();
        if (bankDAO.deleteBank(bankId)) {
            System.out.println("Bank successfully deleted");
        } else {
            System.out.println("Error while deleting bank");
        }
    }



}
