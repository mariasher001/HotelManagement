package com.marry.hotelmanagement.Models;

import javax.persistence.*;

@Entity
public class CheckInData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Basic(optional = false)
    private String name;
    @Basic(optional = false)
    private String typeOfID;
    @Basic(optional = false)
    private String idNo;
    @Basic(optional = false)
    private String noOfAdditionalMembers;
    @Basic(optional = false)
    private String pets;
    @Basic(optional = false)
    private String petTag;
    @Basic(optional = false)
    private String checkInDate;
    @Basic(optional = false)
    private String expectedCheckOut;
    @Basic(optional = false)
    private String estimatedStay;
    @Basic(optional = false)
    private String roomNo;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeOfID() {
        return typeOfID;
    }

    public void setTypeOfID(String typeOfID) {
        this.typeOfID = typeOfID;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getNoOfAdditionalMembers() {
        return noOfAdditionalMembers;
    }

    public void setNoOfAdditionalMembers(String noOfAdditionalMembers) {
        this.noOfAdditionalMembers = noOfAdditionalMembers;
    }

    public String getPets() {
        return pets;
    }

    public void setPets(String pets) {
        this.pets = pets;
    }

    public String getPetTag() {
        return petTag;
    }

    public void setPetTag(String petTag) {
        this.petTag = petTag;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getExpectedCheckOut() {
        return expectedCheckOut;
    }

    public void setExpectedCheckOut(String expectedCheckOut) {
        this.expectedCheckOut = expectedCheckOut;
    }

    public String getEstimatedStay() {
        return estimatedStay;
    }

    public void setEstimatedStay(String estimatedStay) {
        this.estimatedStay = estimatedStay;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }
}
