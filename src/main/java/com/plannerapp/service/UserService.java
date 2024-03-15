package com.plannerapp.service;

import com.plannerapp.model.DTo.UserLogin;
import com.plannerapp.model.DTo.UserRegister;

public interface UserService {
    boolean register(UserRegister userRegister);

    boolean login(UserLogin userLogin);

    void logout();

}
