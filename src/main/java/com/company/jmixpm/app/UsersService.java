package com.company.jmixpm.app;

import com.company.jmixpm.entity.Project;
import com.company.jmixpm.entity.User;
import io.jmix.core.FetchPlan;
import io.jmix.core.FetchPlans;
import io.jmix.data.PersistenceHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UsersService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private FetchPlans fetchPlans;

    @Transactional
    public List<User> getUsersNotInProject(Project project) {
        /*FetchPlan userFetchPlan = fetchPlans.builder(User.class)
                .add("username")
                .partial()
                .build();*/

        return entityManager.createQuery("select u from User u " +
                "where u.id NOT IN " +
                "(select u1.id from User u1 " +
                    "INNER JOIN u1.projects pul " +
                    "WHERE pul.id = ?1)", User.class)
                .setParameter(1, project.getId())
                .getResultList();
    }
}
