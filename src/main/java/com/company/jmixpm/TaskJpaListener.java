package com.company.jmixpm;

import com.company.jmixpm.entity.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

public class TaskJpaListener {

    private static final Logger log = LoggerFactory.getLogger(TaskJpaListener.class);

    @PreUpdate
    @PreRemove
    @PrePersist
    public void beforeUpdate(Task task) {
        log.info(task.getClass().getSimpleName() + " before update task: " + task.getName() + " id: " + task.getId());
    }

    @PostPersist
    @PostUpdate
    @PostRemove
    public void afterUpdate(Task task) {
        log.info(task.getClass().getSimpleName() + " after update task: " + task.getName() + " id: " + task.getId());
    }

    @PostLoad
    public void postLoad(Task task) {
        log.info("JPA listener PostLoad: " + task.getId());
    }
}
