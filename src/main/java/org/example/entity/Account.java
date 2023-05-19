package org.example.entity;

import javax.persistence.*;
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
    @Column(name="solde", precision=10, scale=2)
    private Double Solde;
    @ManyToOne
    @JoinColumn(name = "id_bank")
    private Bank bank;
    @ManyToMany(mappedBy = "account")
    private List<Customer> customers;

    public Account() {
    }

    public Account(String libelle, String IBAN, Double solde) {
        this.libelle = libelle;
        this.IBAN = IBAN;
        Solde = solde;
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

    public Double getSolde() {
        return Solde;
    }

    public void setSolde(Double solde) {
        Solde = solde;
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
                ", Solde=" + Solde +
                ", bank=" + bank +
                ", customers=" + customers +
                '}';
    }
}
