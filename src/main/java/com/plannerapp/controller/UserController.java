package com.plannerapp.controller;

import com.plannerapp.currantLogginUser.LoginUser;
import com.plannerapp.model.DTo.UserLogin;
import com.plannerapp.model.DTo.UserRegister;
import com.plannerapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;
    private final LoginUser loginUser;

    public UserController(UserService userService, LoginUser loginUser) {
        this.userService = userService;
        this.loginUser = loginUser;
    }
    @ModelAttribute("userRegistered")
    public UserRegister initForm(){
        return new UserRegister();
    }
    @ModelAttribute("userLogin")
    public UserLogin initLogin(){
        return new UserLogin();
    }

    @GetMapping("/login")
    public String login(){
        if (loginUser.isLogin()) {
            return "redirect:/home";
        }
        return "login";
    }


    @GetMapping("/register")
    public String register(){
        if (loginUser.isLogin()) {
            return "redirect:/home";
        }

        return "register";
    }

    @PostMapping("/register")
    public String register( @Valid UserRegister userRegister,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){
        if (loginUser.isLogin()) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegister",bindingResult);
            redirectAttributes.addFlashAttribute("userRegister", userRegister);
            return "redirect:/register";
        }
        boolean isRegister = this.userService.register(userRegister);
        if(!isRegister) {
            return "redirect:/register";
        }
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@Valid UserLogin userLogin,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {
        if (loginUser.isLogin()) {
            return "redirect:/home";
        }
        boolean isLog = this.userService.login(userLogin);

        if (bindingResult.hasErrors() || !isLog) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLogin",bindingResult);
            redirectAttributes.addFlashAttribute("userLogin", userLogin);
            return "redirect:/login";
        }

          return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(){
            this.userService.logout();
        return "redirect:/login";
    }
}
