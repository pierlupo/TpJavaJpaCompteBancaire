package org.example.impl;

import org.example.dao.BankDAO;
import org.example.entity.Bank;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class BankDAOImpl implements BankDAO {

    private EntityManagerFactory entityManagerFactory;

    public BankDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
    @Override
    public boolean addBank(Bank bank) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(bank);
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
    public List<Bank> getAllBanks() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Bank> banks = entityManager.createQuery("Select b from Bank b",Bank.class).getResultList();
        entityManager.close();
        return banks;
    }

    @Override
    public boolean deleteBank(Long bankId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Bank bank = entityManager.find(Bank.class,bankId);
            if(bank != null){
                entityManager.remove(bank);
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

    @Override
    public List<Bank> getBankByCustomerId(Long customerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Bank> banks = entityManager.createQuery("SELECT b FROM Bank b WHERE b.customer.id = :customerId ")
                .setParameter("customerId", customerId)
                .getResultList();
        return banks;
    }
}
