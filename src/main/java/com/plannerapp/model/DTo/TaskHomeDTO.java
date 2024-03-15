package com.plannerapp.model.DTo;

import com.plannerapp.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskHomeDTO {

    private List<GetAllTask> assignedTasksToMe;
    private List<GetAllTask> availableTasks;

    public TaskHomeDTO (){
        this.assignedTasksToMe = new ArrayList<>();
        this.availableTasks = new ArrayList<>();
    }

    public List<GetAllTask> getAssignedTasksToMe() {
        return assignedTasksToMe;
    }

    public void setAssignedTasksToMe(List<GetAllTask> assignedTasksToMe) {
        this.assignedTasksToMe = assignedTasksToMe;
    }

    public List<GetAllTask> getAvailableTasks() {
        return availableTasks;
    }

    public void setAvailableTasks(List<GetAllTask> availableTasks) {
        this.availableTasks = availableTasks;
    }

    public int size(){
        return this.availableTasks.size();
    }




}
