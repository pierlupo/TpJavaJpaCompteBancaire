package org.example.dao;

import org.example.entity.Operation;

import java.util.List;

public interface OperationDAO {

    public boolean addOperation(Operation operation);

    public List<Operation> getAllOperations();

    public boolean deleteOperation(int operationId);

    public List<Operation> getOperationByCustomerId(Long customerId);
}
