package com.marry.hotelmanagement.Models;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class CheckInDataManager implements CheckInDataDAO{

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public CheckInDataManager() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("HotelManagement");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void setCheckInData(CheckInData checkInData) {
        entityManager.getTransaction().begin();
        entityManager.persist(checkInData);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteCheckInData(CheckInData checkInData) {
        entityManager.getTransaction().begin();
        entityManager.remove(checkInData);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateCheckInData(CheckInData checkInData) {
        entityManager.getTransaction().begin();
        entityManager.merge(checkInData);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<CheckInData> readAllCheckInData() {
        TypedQuery<CheckInData> query = entityManager.createQuery("Select checkInData FROM CheckInData checkInData",CheckInData.class);
        return query.getResultList();
    }

    @Override
    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }

}
