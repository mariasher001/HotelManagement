package com.marry.hotelmanagement.Models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class CustomerData {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

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
    private String checkOutDate;
    @Basic(optional = false)
    private String roomNo;
    @Basic(optional = false)
    private String status;
    @Basic(optional = false)
    private String billId;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }
}
