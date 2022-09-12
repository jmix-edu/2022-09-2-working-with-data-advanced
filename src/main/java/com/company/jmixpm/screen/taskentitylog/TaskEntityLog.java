package com.company.jmixpm.screen.taskentitylog;

import com.company.jmixpm.entity.Task;
import io.jmix.ui.component.ComboBox;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@UiController("TaskEntityLog")
@UiDescriptor("task-entity-log.xml")
public class TaskEntityLog extends Screen {

    @Autowired
    private ComboBox<UUID> taskField;

    @Subscribe(id = "tasksDc", target = Target.DATA_CONTAINER)
    public void onTasksDcCollectionChange(CollectionContainer.CollectionChangeEvent<Task> event) {
        List<Task> tasks = event.getSource().getItems();
        taskField.setOptionsMap(tasks.stream()
                .collect(Collectors.toMap(Task::getName, Task::getId)));
    }
}