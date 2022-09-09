package com.company.jmixpm.screen.project;

import com.company.jmixpm.app.ProjectService;
import com.company.jmixpm.app.datatype.ProjectLabels;
import com.company.jmixpm.screen.user.UserBrowse;
import io.jmix.core.validation.group.UiComponentChecks;
import io.jmix.core.validation.group.UiCrossFieldChecks;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.TextArea;
import io.jmix.ui.component.validation.Validator;
import io.jmix.ui.component.validator.BeanPropertyValidator;
import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.groups.Default;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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

    @Autowired
    private javax.validation.Validator validator;

    @Install(to = "usersTable.add", subject = "screenConfigurer")
    private void usersTableAddScreenConfigurer(Screen screen) {
        ((UserBrowse) screen).setProject(getEditedEntity());
    }

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
            showErrorValidation((Set) e.getConstraintViolations());
        }
    }

    @Subscribe("performBeanValidation")
    public void onPerformBeanValidationClick(Button.ClickEvent event) {
        Set<ConstraintViolation<Project>> constraintViolations = validator.validate(getEditedEntity(),
                Default.class, UiComponentChecks.class, UiCrossFieldChecks.class);

        showErrorValidation(constraintViolations);
    }

    private void showErrorValidation(Set<ConstraintViolation<Project>> violations) {
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<?> constraintViolation : violations) {
            sb.append(constraintViolation.getMessage()).append("\n");
        }

        notifications.create(Notifications.NotificationType.TRAY)
                .withCaption(sb.toString())
                .show();
    }
}