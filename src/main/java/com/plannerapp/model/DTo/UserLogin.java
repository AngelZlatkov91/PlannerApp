package com.plannerapp.model.DTo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserLogin {
    @NotBlank
    @Size(min = 3,max = 20,message = "Username length must be between 3 and 20 characters!")
    private String username;
    @NotBlank
    @Size(min = 3,max = 20, message = "Password length must be between 3 and 20 characters!")
    private String password;

    public UserLogin (){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
