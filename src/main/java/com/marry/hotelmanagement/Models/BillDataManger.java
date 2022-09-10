package com.marry.hotelmanagement.Models;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class BillDataManger implements BillDataDAO{
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public BillDataManger() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("HotelManagement");
        this.entityManager = entityManagerFactory.createEntityManager();
    }
    
    public void setBillData(BillData billData) {
        entityManager.getTransaction().begin();
        entityManager.persist(billData);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteBillData(BillData billData) {
        entityManager.getTransaction().begin();
        entityManager.remove(billData);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateBillData(BillData billData) {
        entityManager.getTransaction().begin();
        entityManager.merge(billData);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<BillData> readAllBillData() {
        TypedQuery<BillData> query = entityManager.createQuery("Select billData FROM BillData billData",BillData.class);
        return query.getResultList();
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();

    }
}
