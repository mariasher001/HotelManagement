package com.marry.hotelmanagement.Models;

import java.util.List;

public interface CustomerDataDAO extends AutoCloseable{
    public void setCustomerData(CustomerData customerData);
    public void deleteCustomerData(CustomerData customerData);
    public void updateCustomerData(CustomerData customerData);
    public List<CustomerData> readAllCustomerData();


}
