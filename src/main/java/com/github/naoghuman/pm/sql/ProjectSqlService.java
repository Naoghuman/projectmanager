/*
 * Copyright (C) 2018 Naoghuman
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.naoghuman.pm.sql;

import com.github.naoghuman.lib.database.core.DatabaseFacade;
import com.github.naoghuman.lib.logger.core.LoggerFacade;
import com.github.naoghuman.pm.configuration.DefaultConfiguration;
import static com.github.naoghuman.pm.configuration.DefaultConfiguration.DEFAULT_ID;
import com.github.naoghuman.pm.configuration.ProjectConfiguration;
import static com.github.naoghuman.pm.configuration.ProjectTypeConfiguration.NAMED_QUERY__NAME__FIND_ALL_WITH_NAME;
import static com.github.naoghuman.pm.configuration.ProjectTypeConfiguration.PROJECTTYPE__COLUMN_NAME__NAME;
import static com.github.naoghuman.pm.configuration.ProjectTypeConfiguration.PROJECTTYPE__NAME_DEFECT;
import static com.github.naoghuman.pm.configuration.ProjectTypeConfiguration.PROJECTTYPE__NAME_PROJECT;
import static com.github.naoghuman.pm.configuration.ProjectTypeConfiguration.PROJECTTYPE__NAME_TEMPLATE;
import com.github.naoghuman.pm.converter.ColorConverter;
import com.github.naoghuman.pm.model.Project;
import com.github.naoghuman.pm.model.ProjectType;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
final class ProjectSqlService implements DefaultConfiguration, ProjectConfiguration {
    
    private static final Optional<ProjectSqlService> INSTANCE = Optional.of(new ProjectSqlService());

    public static final ProjectSqlService getDefault() {
        return INSTANCE.get();
    }
    
    private ProjectSqlService() {
        
    }
    
    void create(final Project project) {
        LoggerFacade.getDefault().debug(this.getClass(), "ProjectSqlService.create(Project)"); // NOI18N
        
        if (Objects.equals(project.getId(), DEFAULT_ID)) {
            project.setId(System.currentTimeMillis());
            
            DatabaseFacade.getDefault().getCrudService().create(project);
        }
        else {
            this.update(project);
        }
    }

    void initializeTestData() {
        LoggerFacade.getDefault().info(this.getClass(), "ProjectSqlService.initializeTestData()"); // NOI18N
        
        final String PROJECT__TEST_DATA__PROJECT_A = "Project A"; // NOI18N
        final String PROJECT__TEST_DATA__PROJECT_B = "Project B"; // NOI18N
        final String PROJECT__TEST_DATA__PROJECT_C = "Project C"; // NOI18N
        
        // public Project(final long id, final long generationTime, 
        // final String name, final long projectType)
        
        final ObservableList<Project> projects = FXCollections.observableArrayList();
        projects.addAll(this.findAllProjectsWithName(PROJECT__TEST_DATA__PROJECT_A));
        if (projects.isEmpty()) {
            final ObservableList<ProjectType> projectTypes = FXCollections.observableArrayList();
            projectTypes.addAll(SqlProvider.getDefault().findAllProjectTypesWithName(PROJECTTYPE__NAME_DEFECT));
            
            final long projectTypeId = !projectTypes.isEmpty() ? projectTypes.get(0).getId() : System.nanoTime();
            ProjectSqlService.getDefault().create(new Project(
                    System.nanoTime(), System.nanoTime(),
                    PROJECT__TEST_DATA__PROJECT_A, projectTypeId));
        }
        
        projects.clear();
        projects.addAll(this.findAllProjectsWithName(PROJECT__TEST_DATA__PROJECT_B));
        if (projects.isEmpty()) {
            final ObservableList<ProjectType> projectTypes = FXCollections.observableArrayList();
            projectTypes.addAll(SqlProvider.getDefault().findAllProjectTypesWithName(PROJECTTYPE__NAME_PROJECT));
            
            final long projectTypeId = !projectTypes.isEmpty() ? projectTypes.get(0).getId() : System.nanoTime();
            ProjectSqlService.getDefault().create(new Project(
                    System.nanoTime(), System.nanoTime(),
                    PROJECT__TEST_DATA__PROJECT_B, projectTypeId));
        }
        
        projects.clear();
        projects.addAll(this.findAllProjectsWithName(PROJECT__TEST_DATA__PROJECT_C));
        if (projects.isEmpty()) {
            final ObservableList<ProjectType> projectTypes = FXCollections.observableArrayList();
            projectTypes.addAll(SqlProvider.getDefault().findAllProjectTypesWithName(PROJECTTYPE__NAME_TEMPLATE));
            
            final long projectTypeId = !projectTypes.isEmpty() ? projectTypes.get(0).getId() : System.nanoTime();
            ProjectSqlService.getDefault().create(new Project(
                    System.nanoTime(), System.nanoTime(),
                    PROJECT__TEST_DATA__PROJECT_C, projectTypeId));
        }
    }
    
    ObservableList<Project> findAllProjects() {
        LoggerFacade.getDefault().debug(this.getClass(), "ProjectSqlService.findAllProjects(): ObservableList<ProjectType>"); // NOI18N
        
        final ObservableList<Project> allProjects = FXCollections.observableArrayList();
        final List<Project> projects = DatabaseFacade.getDefault().getCrudService()
                .findByNamedQuery(Project.class, NAMED_QUERY__NAME__FIND_ALL);
        
        allProjects.addAll(projects);
        Collections.sort(allProjects);

        return allProjects;
    }
    
    ObservableList<Project> findAllProjectsWithName(final String name) {
        LoggerFacade.getDefault().debug(this.getClass(), "ProjectSqlService.findAllProjectsWithName(String): ObservableList<Project>"); // NOI18N
        
        final ObservableList<Project> allProjects = FXCollections.observableArrayList();
        final Map<String, Object> parameters = FXCollections.observableHashMap();
        parameters.put(PROJECTTYPE__COLUMN_NAME__NAME, name);
        
        final List<Project> projects = DatabaseFacade.getDefault().getCrudService()
                .findByNamedQuery(Project.class, NAMED_QUERY__NAME__FIND_ALL_WITH_NAME, parameters);
        
        allProjects.addAll(projects);
        Collections.sort(allProjects);

        return allProjects;
    }
    
    void update(final Project project) {
        LoggerFacade.getDefault().debug(this.getClass(), "ProjectSqlService.update(Project)"); // NOI18N
        
        DatabaseFacade.getDefault().getCrudService().update(project);
    }
    
}
