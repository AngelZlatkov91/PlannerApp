package com.plannerapp.init;

import com.plannerapp.model.Enums.PriorityEnumsName;
import com.plannerapp.model.Priority;
import com.plannerapp.repo.PriorityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class PriorityInit implements CommandLineRunner {
    private final PriorityRepository priorityRepository;

    public PriorityInit(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.priorityRepository.count() ==0) {
            List<Priority> priorities = new ArrayList<>();
            Arrays.stream(PriorityEnumsName.values()).forEach(
                    name -> {
                        Priority priority = new Priority();
                        priority.setName(name);
                        priorities.add(priority);
                    }
            );
            this.priorityRepository.saveAll(priorities);
        }

    }
}
