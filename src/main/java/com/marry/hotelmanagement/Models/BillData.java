package com.marry.hotelmanagement.Models;

import javafx.fxml.FXML;

import javax.persistence.*;

@Entity
public class BillData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic(optional = false)
    private String billId;

    @Basic(optional = false)

    private int customerDataId;

    @Basic(optional = false)
    private int amount;

    @Basic(optional = false)
    private String billStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBillId() {
        return billId;
    }

    public int getCustomerDataId() {
        return customerDataId;
    }

    public void setCustomerDataId(int customerDataId) {
        this.customerDataId = customerDataId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }
}
