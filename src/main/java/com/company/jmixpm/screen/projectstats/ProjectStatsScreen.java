package com.company.jmixpm.screen.projectstats;

import com.company.jmixpm.entity.ProjectStats;
import io.jmix.core.LoadContext;
import io.jmix.core.Metadata;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UiController("ProjectStatsScreen")
@UiDescriptor("project-stats-screen.xml")
public class ProjectStatsScreen extends Screen {

    @Autowired
    private Metadata metadata;

    @Install(to = "statsDl", target = Target.DATA_LOADER)
    private List<ProjectStats> statsDlLoadDelegate(LoadContext<ProjectStats> loadContext) {
        ProjectStats stats = metadata.create(ProjectStats.class);
        stats.setProjectName("test project");
        stats.setTaskCount(2);
        stats.setActualEfforts(12);
        stats.setPlannedEfforts(12);
        return List.of(stats);
    }
}