package org.example.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_id")
    private Long id;
    @Column(name = "bank_name")
    private String name;
    private String address;
    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts;

    public Bank() {
    }

    public Bank(String address) {
        this.address = address;
    }

    public Bank(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", address='" + address + '\'' +
                '}';
    }
}
