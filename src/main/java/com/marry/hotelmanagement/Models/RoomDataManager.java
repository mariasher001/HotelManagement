package com.marry.hotelmanagement.Models;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class RoomDataManager implements RoomDataDAO{

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public RoomDataManager() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("HotelManagement");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void setRoomData(RoomData roomData) {
        entityManager.getTransaction().begin();
        entityManager.persist(roomData);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteRoomData(RoomData roomData) {
        entityManager.getTransaction().begin();
        entityManager.remove(roomData);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateRoomData(RoomData roomData) {
        entityManager.getTransaction().begin();
        entityManager.merge(roomData);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<RoomData> readAllRoomData() {
        TypedQuery<RoomData> query = entityManager.createQuery("Select roomData FROM RoomData roomData",RoomData.class);
        return query.getResultList();
    }

    @Override
    public void close() throws Exception {

    }
}
