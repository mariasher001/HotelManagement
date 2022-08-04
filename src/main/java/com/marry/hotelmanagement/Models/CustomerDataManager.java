package com.marry.hotelmanagement.Models;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class CustomerDataManager implements CustomerDataDAO{
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public CustomerDataManager() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("HotelManagement");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void setCustomerData(CustomerData customerData) {
        entityManager.getTransaction().begin();
        entityManager.persist(customerData);
        entityManager.getTransaction().commit();

    }

    @Override
    public void deleteCustomerData(CustomerData customerData) {
        entityManager.getTransaction().begin();
        entityManager.remove(customerData);
        entityManager.getTransaction().commit();

    }

    @Override
    public void updateCustomerData(CustomerData customerData) {
        entityManager.getTransaction().begin();
        entityManager.merge(customerData);
        entityManager.getTransaction().commit();

    }

    @Override
    public List<CustomerData> readAllCustomerData() {
        TypedQuery<CustomerData> query = entityManager.createQuery("SELECT customerData FROM CustomerData customerData",CustomerData.class);
        return query.getResultList();
    }

    @Override
    public void close() throws Exception {

    }
}
