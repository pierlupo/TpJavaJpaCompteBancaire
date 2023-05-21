package org.example.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operation_id")
    private int id;
    private double amount;
    private OperationStatus status;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "operation_account", joinColumns = @JoinColumn(name = "id_operation"), inverseJoinColumns = @JoinColumn(name = "id_account"))
    private List<Account> accounts;
    @Column(name = "account_id")
    private int accountId;

    public Operation() {
    }

    public int getAccountId() {
        return accountId;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public OperationStatus getStatus() {
        return status;
    }

    public Operation(double amount, int accountId) {
        this.amount = amount;
        this.accountId = accountId;
        this.status = (this.amount >= 0) ? OperationStatus.DEPOSIT : OperationStatus.WITHDRAWL;
    }

    public Operation(int id, double amount, int accountId) {
        this(amount, accountId);
        this.id = id;
    }
    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", amount=" + amount +
                ", status=" + status +
                ", accountId=" + accountId +
                '}';
    }
}
enum OperationStatus {
    DEPOSIT,
    WITHDRAWL
}

