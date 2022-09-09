package com.company.jmixpm.security;

import com.company.jmixpm.entity.TimeEntry;
import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;

@RowLevelRole(name = "Restricted time entries", code = "restricted-entries")
public interface RestrictedTimeEntries {

    @JpqlRowLevelPolicy(entityClass = TimeEntry.class, where = "{E}.user.id = :current_user_id")
    void timeEntries();
}
