package com.marry.hotelmanagement.Models;

import javax.persistence.*;

@Entity(name = "RoomData")
public class RoomData {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @Basic(optional = false)
    private String roomNo;
    @Basic(optional = false)
    private String typeOfRoom;
    @Basic(optional = false)
    private String noOfBeds;
    @Basic(optional = false)
    private String price;
    @Basic(optional = false)
    private String roomAvailability;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public RoomData() {
    }

    public RoomData(String roomNo, String typeOfRoom, String noOfBeds, String price, String roomAvailability) {
        this.roomNo = roomNo;
        this.typeOfRoom = typeOfRoom;
        this.noOfBeds = noOfBeds;
        this.price = price;
        this.roomAvailability = roomAvailability;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getTypeOfRoom() {
        return typeOfRoom;
    }

    public void setTypeOfRoom(String typeOfRoom) {
        this.typeOfRoom = typeOfRoom;
    }

    public String getNoOfBeds() {
        return noOfBeds;
    }

    public void setNoOfBeds(String noOfBeds) {
        this.noOfBeds = noOfBeds;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRoomAvailability() {
        return roomAvailability;
    }

    public void setRoomAvailability(String roomAvailability) {
        this.roomAvailability = roomAvailability;
    }
}
