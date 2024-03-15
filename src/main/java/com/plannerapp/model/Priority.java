package com.plannerapp.model;

import com.plannerapp.model.Enums.PriorityEnumsName;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "priorities")
public class Priority extends BaseEntity{

    @Column(unique = true,nullable = false)
    @Enumerated(EnumType.STRING)
    private PriorityEnumsName name;

    @Column(nullable = false)
    private String description;

    @OneToMany  (mappedBy = "priority")
    private Set<Task> task;

    public Priority (){
        this.task = new HashSet<>();
    }

    public PriorityEnumsName getName() {
        return name;
    }

    public void setName(PriorityEnumsName name) {
        this.name = name;
        setDescription(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(PriorityEnumsName name) {
        String description = "";
        switch (name) {
            case LOW -> description = "Should be fixed if time permits but can be postponed.";
            case IMPORTANT -> description = "A core functionality that your product is explicitly supposed to perform is compromised.";
            case URGENT -> description = "An urgent problem that blocks the system use until the issue is resolved.";
        }

        this.description = description;
    }

    public Set<Task> getTask() {
        return task;
    }

    public void setTask(Set<Task> task) {
        this.task = task;
    }
}
