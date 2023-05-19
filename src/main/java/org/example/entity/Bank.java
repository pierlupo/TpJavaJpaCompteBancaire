package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name="bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_id")
    private Long id;
    private String address;

    public Bank() {
    }

    public Bank(String address) {
        this.address = address;
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
