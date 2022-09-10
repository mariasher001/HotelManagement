package com.marry.hotelmanagement.Models;

import java.util.List;

public interface BillDataDAO extends AutoCloseable{
    public void setBillData(BillData billData);

    public void deleteBillData(BillData billData);

    public void updateBillData(BillData billData);

    public List<BillData> readAllBillData();
}
