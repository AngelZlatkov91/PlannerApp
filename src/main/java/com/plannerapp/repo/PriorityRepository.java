package com.plannerapp.repo;

import com.plannerapp.model.Enums.PriorityEnumsName;
import com.plannerapp.model.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {


    Priority findByName(PriorityEnumsName priority);

}
