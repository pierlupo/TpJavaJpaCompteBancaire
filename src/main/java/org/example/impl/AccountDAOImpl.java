package org.example.impl;

import org.example.dao.AccountDAO;
import org.example.entity.Account;
import org.example.entity.Customer;
import org.example.entity.Operation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Collections;
import java.util.List;

public class AccountDAOImpl implements AccountDAO {
    private EntityManagerFactory entityManagerFactory;

    public AccountDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public boolean addAccount(Account account) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(account);
            transaction.commit();
            return true;
        }catch (Exception e){
            if(transaction.isActive()){
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }finally {
            entityManager.close();
        }
    }
    public boolean addAccount(Account account, Long customerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Customer customer = entityManager.find(Customer.class,customerId);
            account.setCustomers(Collections.singletonList(customer));
            customer.getAccounts().add(account);
            entityManager.persist(account);
            transaction.commit();
            entityManager.close();
            entityManager.persist(account);
            transaction.commit();
            return true;
        }catch (Exception e){
            if(transaction.isActive()){
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }finally {
            entityManager.close();
        }
    }


    @Override
    public List<Account> getAllAccounts() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Account> accounts = entityManager.createQuery("Select a from Account a",Account.class).getResultList();
        entityManager.close();
        return accounts;
    }

    @Override
    public boolean deleteAccount(Long accountId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Account account = entityManager.find(Account.class,accountId);
            if(account != null){
                entityManager.remove(account);
                transaction.commit();
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            if(transaction.isActive()){
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }finally {
            entityManager.close();
        }
    }

//    @Override
//    public boolean makeDeposit(Operation operation) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction transaction = entityManager.getTransaction();
//        try {
//            transaction.begin();
//            getAccountByCustomerId(customerId);
//            Account account = entityManager.find(Account.class,accountId);
//            if(account != null){
//                entityManager.remove(account);
//                transaction.commit();
//                return true;
//            } else {
//                return false;
//            }
//        }catch (Exception e){
//            if(transaction.isActive()){
//                transaction.rollback();
//            }
//            e.printStackTrace();
//            return false;
//        }finally {
//            entityManager.close();
//        }
//    }
//
//    @Override
//    public boolean makeWithDrawl(Operation operation) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction transaction = entityManager.getTransaction();
//    }
//
//    @Override
//    public boolean updateAccount() {
//        return false;
//    }

    @Override
    public List<Account> getAccountByCustomerId(Long customerId) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Account> accounts = entityManager.createQuery("SELECT a FROM Account a WHERE a.customer.id = :customerId")
                .setParameter("customerId",customerId)
                .getResultList();
        return accounts;
    }
}
