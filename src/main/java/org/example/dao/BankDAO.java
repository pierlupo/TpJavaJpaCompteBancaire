package org.example.dao;

import org.example.entity.Bank;

import java.util.List;

public interface BankDAO {


    public boolean addBank(Bank bank);

    public List<Bank> getAllBanks();

    public boolean deleteBank(Long bankId);

    public List<Bank> getBankByCustomerId(Long customerId);
}
