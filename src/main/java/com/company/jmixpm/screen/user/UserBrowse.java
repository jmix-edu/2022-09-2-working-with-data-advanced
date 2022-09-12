package com.company.jmixpm.screen.user;

import com.company.jmixpm.app.UsersService;
import com.company.jmixpm.entity.Project;
import com.company.jmixpm.entity.User;
import com.company.jmixpm.screen.userprojectsdialog.UserProjectsDialog;
import io.jmix.core.DataManager;
import io.jmix.core.LoadContext;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.Filter;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.navigation.Route;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UiController("User.browse")
@UiDescriptor("user-browse.xml")
@LookupComponent("usersTable")
@Route("users")
public class UserBrowse extends StandardLookup<User> {

    @Autowired
    private GroupTable<User> usersTable;

    @Autowired
    private DataManager dataManager;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ScreenBuilders screenBuilders;
    private Project project;

    @Autowired
    private Filter filter;

    @Install(to = "usersDl", target = Target.DATA_LOADER)
    private List<User> usersDlLoadDelegate(LoadContext<User> loadContext) {
        if (project != null) {
            return usersService.getUsersNotInProject(project);
        } else {
            return dataManager.loadList(loadContext);
        }
    }

    public void setProject(Project project) {
        this.project = project;

        filter.setVisible(false);
    }

    @Subscribe("usersTable.showUserProjects")
    public void onUsersTableShowUserProjects(Action.ActionPerformedEvent event) {
        UserProjectsDialog userProjectsDialog = screenBuilders.screen(this)
                .withScreenClass(UserProjectsDialog.class)
                .build();

        userProjectsDialog.setUser(usersTable.getSingleSelected());
        userProjectsDialog.show();
    }
}