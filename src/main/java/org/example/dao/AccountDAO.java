package org.example.dao;
import java.util.List;
import org.example.entity.Account;
import org.example.entity.Operation;

public interface AccountDAO {

    public boolean addAccount(Account account);

    public List<Account> getAllAccounts();

    public boolean deleteAccount(Long accountId);

//    public boolean makeDeposit(Operation operation);
//
//    public boolean makeWithDrawl(Operation operation);
//
//    public boolean updateAccount();

    public List<Account> getAccountByCustomerId(Long customerId);
}
