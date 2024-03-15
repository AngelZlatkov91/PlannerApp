package com.plannerapp.model.DTo;

import com.plannerapp.model.Enums.PriorityEnumsName;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class TaskAddDTO {
    @Size(min = 2,max = 50, message = "Description must be between 2 and 50 character!")
    @NotBlank
    private String description;
    @Future (message = "Due Date must be in the future!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    private PriorityEnumsName priority;

    public TaskAddDTO (){}

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
}
