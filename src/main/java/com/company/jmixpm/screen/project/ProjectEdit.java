package com.company.jmixpm.screen.project;

import com.company.jmixpm.app.ProjectService;
import com.company.jmixpm.app.datatype.ProjectLabels;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.TextArea;
import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

@UiController("Project.edit")
@UiDescriptor("project-edit.xml")
@EditedEntityContainer("projectDc")
public class ProjectEdit extends StandardEditor<Project> {

    @Autowired
    private TextArea<ProjectLabels> projectLabelsField;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private Notifications notifications;

    @Subscribe
    public void onInitEntity(InitEntityEvent<Project> event) {
        projectLabelsField.setEditable(true);

        event.getEntity().setProjectLabels(new ProjectLabels(List.of("bug", "task", "enhancement")));

    }

    @Subscribe("commitWithBeanValidation")
    public void onCommitWithBeanValidationClick(Button.ClickEvent event) {
        try {
            projectService.saveProject(getEditedEntity());
        } catch (ConstraintViolationException e) {
            StringBuilder sb = new StringBuilder();

            for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
                sb.append(constraintViolation.getMessage()).append("\n");
            }

            notifications.create(Notifications.NotificationType.TRAY)
                    .withCaption(sb.toString())
                    .show();
        }
    }
}