package com.plannerapp.service.impl;

import com.plannerapp.currantLogginUser.LoginUser;
import com.plannerapp.model.DTo.UserLogin;
import com.plannerapp.model.DTo.UserRegister;
import com.plannerapp.model.User;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final LoginUser loginUser;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, LoginUser loginUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loginUser = loginUser;
    }
    @Override
    public boolean register(UserRegister userRegister) {
        if (!userRegister.getPassword().equals(userRegister.getConfirmPassword())) {
            return false;
        }

        Optional<User> dbUser = this.userRepository.findByUsername(userRegister.getUsername());
        if (dbUser.isPresent()) {
            return false;
        }
        Optional<User> byEmail = this.userRepository.findByEmail(userRegister.getEmail());
        if (byEmail.isPresent()) {
            return false;
        }

        User user = new User(userRegister);
        user.setPassword(passwordEncoder.encode(userRegister.getPassword()));
        this.userRepository.save(user);
        return true;
    }

    @Override
    public boolean login(UserLogin userLogin) {
        Optional<User> byUsername = this.userRepository.findByUsername(userLogin.getUsername());
        if (byUsername.isPresent() && passwordEncoder.matches(userLogin.getPassword(),byUsername.get().getPassword())) {
            this.loginUser.login(userLogin.getUsername());
            return true;
        }
        return false;
    }

    @Override
    public void logout() {
        this.loginUser.logout();
    }
}
