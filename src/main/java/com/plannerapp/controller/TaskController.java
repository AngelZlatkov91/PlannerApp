package com.plannerapp.controller;

import com.plannerapp.currantLogginUser.LoginUser;
import com.plannerapp.model.DTo.TaskAddDTO;
import com.plannerapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class TaskController {
    private final TaskService taskService;
    private final LoginUser loginUser;

    public TaskController(TaskService taskService, LoginUser loginUser) {
        this.taskService = taskService;
        this.loginUser = loginUser;
    }


    @ModelAttribute("taskAddDTO")
    public TaskAddDTO initForm(){
        return new TaskAddDTO();
    }


    @GetMapping("/task-add")
    public ModelAndView add(){
        if (!this.loginUser.isLogin()) {
            return new ModelAndView("redirect:/login");
        }
        return new ModelAndView("task-add");
    }

    @PostMapping("/task-add")
    public String add (@Valid TaskAddDTO taskAddDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {
        if (!loginUser.isLogin()) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.taskAddDTO",bindingResult);
            redirectAttributes.addFlashAttribute("taskAddDTO", taskAddDTO);
            return "redirect:/task-add";
        }
        this.taskService.add(taskAddDTO);

        return "redirect:/home";
    }

    @PostMapping("/task/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        if (!loginUser.isLogin()) {
            return new ModelAndView("redirect:/login");
        }
        this.taskService.remove(id);
        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/task/assign/{id}")
    public ModelAndView assign(@PathVariable("id") Long id){
        if (!loginUser.isLogin()) {
            return new ModelAndView("redirect:/login");
        }
        this.taskService.assign(id);
        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/task/return/{id}")
    public ModelAndView returnTask(@PathVariable("id") Long id){
        if (!loginUser.isLogin()) {
            return new ModelAndView("redirect:/login");
        }
        this.taskService.returnTask(id);
        return new ModelAndView("redirect:/home");
    }





}
