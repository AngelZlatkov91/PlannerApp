package com.plannerapp.service;

import com.plannerapp.model.DTo.TaskAddDTO;
import com.plannerapp.model.DTo.TaskHomeDTO;

public interface TaskService {
    void add(TaskAddDTO taskAddDTO);

    void remove(Long id);

    void assign(Long id);

    TaskHomeDTO getHomeViewDate();

    void returnTask(Long id);

}
