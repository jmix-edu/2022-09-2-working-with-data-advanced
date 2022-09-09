package com.company.jmixpm.screen.project;

import io.jmix.core.DataManager;
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

    @Subscribe("saveProject")
    public void onSaveProjectClick(Button.ClickEvent event) {
        Project project = dataManager.load(Project.class).all().list().get(0);

        project.getManager();
    }
}