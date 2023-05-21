package org.example.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;
    @Column(nullable = false)
    private String libelle;
    @Column(name = "iban", nullable = false, length = 27)
    private String IBAN;
    @Column(name="amount", precision=10, scale=2)
    private Double amount;
    @ManyToOne
    @JoinColumn(name = "id_bank")
    private Bank bank;
    @ManyToMany(mappedBy = "accounts")
    private List<Customer> customers;

    @ManyToMany(mappedBy = "accounts")
    private List<Operation> operations;
    private double totalAmount;
    public Account() {
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Account(String libelle, String IBAN, Double amount) {
        this.libelle = libelle;
        this.IBAN = IBAN;
        this.amount = amount;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        amount = amount;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", IBAN='" + IBAN + '\'' +
                ", amount=" + amount +
                ", bank=" + bank +
                ", customers=" + customers +
                '}';
    }
}
