package com.plannerapp.model.DTo;

import com.plannerapp.model.Enums.PriorityEnumsName;
import com.plannerapp.model.Task;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class GetAllTask {
    private Long id;



    private String description;

    private LocalDate dueDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private PriorityEnumsName priority;

    public GetAllTask(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public PriorityEnumsName getPriority() {
        return priority;
    }

    public void setPriority(PriorityEnumsName priority) {
        this.priority = priority;
    }

    public static GetAllTask createFromTask (Task task) {
        GetAllTask getAllTask = new GetAllTask();
        getAllTask.setId(task.getId());
        getAllTask.setDescription(task.getDescription());
        getAllTask.setPriority(task.getPriority().getName());
        getAllTask.setDueDate(task.getDate());
        return getAllTask;
    }
}
