package com.plannerapp.model;

import com.plannerapp.model.DTo.UserRegister;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User  extends BaseEntity{
    @Column(nullable = false,unique = true)
    @NotBlank
    @Size(min=3,max = 20)
    private String username;
    @Column(nullable = false)
    @NotBlank
    private String password;
    @Column(nullable = false,unique = true)
    @Email
    private String email;
    @OneToMany(mappedBy = "user")
    private Set<Task> assignedTasks;

    public User () {
        this.assignedTasks = new HashSet<>();
    }

    public User(UserRegister userRegister) {
        this();
        this.username = userRegister.getUsername();
        this.email = userRegister.getEmail();
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(Set<Task> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }
}
