package com.company.jmixpm.app;

import com.company.jmixpm.entity.Project;
import io.jmix.core.DataManager;
import io.jmix.core.validation.group.UiComponentChecks;
import io.jmix.core.validation.group.UiCrossFieldChecks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

@Validated
@Component
public class ProjectService {

    @Autowired
    private DataManager dataManager;

    @Validated(value = {UiComponentChecks.class, UiCrossFieldChecks.class, Default.class})
    public void saveProject(@NotNull @Valid Project project) {
        dataManager.save(project);
    }
}