package org.example.dao;
import java.util.List;
import org.example.entity.Account;

public interface AccountDAO {

    public boolean addAccount(Account account);

    public List<Account> getAllAccounts();

    public boolean deleteAccount(Long accountId);

    public List<Account> getAccountByCustomerId(Long customerId);
}
