package com.company.jmixpm.screen.project;

import io.jmix.core.DataManager;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Project.browse")
@UiDescriptor("project-browse.xml")
@LookupComponent("projectsTable")
public class ProjectBrowse extends StandardLookup<Project> {

    @Autowired
    private DataManager dataManager;

    @Autowired
    private Notifications notifications;

    @Subscribe("saveProject")
    public void onSaveProjectClick(Button.ClickEvent event) {
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        int newProjectsCount = dataManager.loadValue("select count(e) from Project e " +
                                "where :session_isManager = true " +
                                "and e.status = @enum(com.company.jmixpm.entity.ProjectStatus.NEW) " +
                                "and e.manager.id = :current_user_id",
                        Integer.class)
                .one();

        if (newProjectsCount > 0) {
            notifications.create(Notifications.NotificationType.TRAY)
                    .withCaption("New projects")
                    .withDescription("Projects with NEW status: " + newProjectsCount)
                    .show();
        }
    }
}