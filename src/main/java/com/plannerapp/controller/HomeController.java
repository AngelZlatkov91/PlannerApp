package com.plannerapp.controller;

import com.plannerapp.currantLogginUser.LoginUser;
import com.plannerapp.model.DTo.TaskHomeDTO;
import com.plannerapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final TaskService taskService;
    private final LoginUser loginUser;

    public HomeController(TaskService taskService, LoginUser loginUser) {
        this.taskService = taskService;
        this.loginUser = loginUser;
    }

    @GetMapping("/")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    @GetMapping("/home")
    public ModelAndView home(){
        if (!loginUser.isLogin()) {
            return new ModelAndView("redirect:/login");
        }
        TaskHomeDTO taskHomeDTO = this.taskService.getHomeViewDate();

        return new ModelAndView("home","tasks",taskHomeDTO);
    }





}
