package com.plannerapp.service.impl;

import com.plannerapp.currantLogginUser.LoginUser;
import com.plannerapp.model.DTo.GetAllTask;
import com.plannerapp.model.DTo.TaskAddDTO;
import com.plannerapp.model.DTo.TaskHomeDTO;
import com.plannerapp.model.Priority;
import com.plannerapp.model.Task;
import com.plannerapp.model.User;
import com.plannerapp.repo.PriorityRepository;
import com.plannerapp.repo.TaskRepository;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final LoginUser loginUser;
    private final UserRepository userRepository;
    private final PriorityRepository priorityRepository;


    public TaskServiceImpl(TaskRepository taskRepository, LoginUser loginUser, UserRepository userRepository, PriorityRepository priorityRepository) {
        this.taskRepository = taskRepository;
        this.loginUser = loginUser;
        this.userRepository = userRepository;
        this.priorityRepository = priorityRepository;
    }


    @Override
    public void add(TaskAddDTO taskAddDTO) {
        Priority priority = this.priorityRepository.findByName(taskAddDTO.getPriority());
        Optional<User> byUsername = this.userRepository.findByUsername(loginUser.getUsername());
        Task task = new Task();
        task.setDescription(taskAddDTO.getDescription());
        task.setDate(taskAddDTO.getDueDate());
        task.setPriority(priority);
        this.taskRepository.save(task);
    }

    @Override
    public void remove(Long id) {
        this.taskRepository.deleteById(id);
    }

    @Override
    public void assign(Long id) {
           Optional<Task> optionalTask = this.taskRepository.findById(id);
        if (optionalTask.isPresent()) {
               Task task = optionalTask.get();
            task.setUser(this.userRepository.findByUsername(loginUser.getUsername()).get());
            this.taskRepository.save(task);
           }

    }

    @Override
    public TaskHomeDTO getHomeViewDate() {
        List<GetAllTask> tasks = this.taskRepository.findByUserUsername(loginUser.getUsername()).stream()
                .map(GetAllTask::createFromTask)
                .toList();
        List<GetAllTask> allOtherTasks = this.taskRepository.findByUserIsNull().stream().
                map(GetAllTask::createFromTask)
                        .toList();
             TaskHomeDTO taskHomeDTO = new TaskHomeDTO();
             taskHomeDTO.setAssignedTasksToMe(tasks);
             taskHomeDTO.setAvailableTasks(allOtherTasks);
        return taskHomeDTO;
    }

    @Override
    public void returnTask(Long id) {
        Optional<Task> optionalTask = this.taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setUser(null);
            this.taskRepository.save(task);
        }
    }


}
