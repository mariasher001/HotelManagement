package com.marry.hotelmanagement.Models;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDataManager implements UserDataDAO {
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public UserDataManager() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("HotelManagement");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public void setUserData(UserData userData) {
        entityManager.getTransaction().begin();
        entityManager.persist(userData);
        entityManager.getTransaction().commit();
    }

    public void deleteUserData(UserData userData) {
        entityManager.getTransaction().begin();
        entityManager.remove(userData);
        entityManager.getTransaction().commit();
    }

    public void updateUserData(UserData userData) {
        entityManager.getTransaction().begin();
        entityManager.merge(userData);
        entityManager.getTransaction().commit();
    }

    public List<UserData> readAllUserData() {
        TypedQuery<UserData> query = entityManager.createQuery("SELECT userData FROM UserData userData", UserData.class);
        return query.getResultList();
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }
}
