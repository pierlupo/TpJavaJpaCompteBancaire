package org.example.impl;

import org.example.dao.OperationDAO;
import org.example.entity.Operation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class OperationDAOImpl implements OperationDAO {

    private EntityManagerFactory entityManagerFactory;

    public OperationDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
    @Override
    public boolean addOperation(Operation operation) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(operation);
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
    public List<Operation> getAllOperations() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Operation> operations = entityManager.createQuery("Select o from Operation o", Operation.class).getResultList();
        entityManager.close();
        return operations;
    }

    @Override
    public boolean deleteOperation(int operationId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Operation operation = entityManager.find(Operation.class,operationId);
            if(operation != null){
                entityManager.remove(operation);
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
    public List<Operation> getOperationByCustomerId(Long customerId) {
        return null;
    }
}
