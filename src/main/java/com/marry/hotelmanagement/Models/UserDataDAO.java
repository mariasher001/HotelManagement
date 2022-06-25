package com.marry.hotelmanagement.Models;

import java.util.List;

public interface UserDataDAO extends AutoCloseable{
    public void setUserData(UserData userData);
    public void deleteUserData (UserData userData);
    public void updateUserData(UserData userData);
    public List<UserData> readAllUserData();
}
