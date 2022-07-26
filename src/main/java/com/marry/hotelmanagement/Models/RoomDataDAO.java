package com.marry.hotelmanagement.Models;

import java.util.List;

public interface RoomDataDAO extends AutoCloseable {
    public void setRoomData(RoomData roomData);
    public void deleteRoomData(RoomData roomData);
    public void updateRoomData(RoomData roomData);
    public List<RoomData> readAllRoomData();
}
