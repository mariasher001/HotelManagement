package com.marry.hotelmanagement.Models;

import java.util.List;

public interface CheckInDataDAO extends AutoCloseable {
    public void setCheckInData(CheckInData checkInData);

    public void deleteCheckInData(CheckInData checkInData);

    public void updateCheckInData(CheckInData checkInData);

    public List<CheckInData> readAllCheckInData();
}
