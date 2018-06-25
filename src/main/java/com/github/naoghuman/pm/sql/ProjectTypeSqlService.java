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
import com.github.naoghuman.pm.configuration.ProjectTypeConfiguration;
import com.github.naoghuman.pm.converter.ColorConverter;
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
public final class ProjectTypeSqlService implements DefaultConfiguration, ProjectTypeConfiguration {
    
    private static final Optional<ProjectTypeSqlService> INSTANCE = Optional.of(new ProjectTypeSqlService());

    public static final ProjectTypeSqlService getDefault() {
        return INSTANCE.get();
    }
    
    private ProjectTypeSqlService() {
        
    }
    
    void create(final ProjectType projectType) {
        LoggerFacade.getDefault().debug(this.getClass(), "ProjectTypeSqlService.create(ProjectType)"); // NOI18N
        
        if (Objects.equals(projectType.getId(), DEFAULT_ID)) {
            projectType.setId(System.currentTimeMillis());
            
            DatabaseFacade.getDefault().getCrudService().create(projectType);
        }
        else {
            this.update(projectType);
        }
    }
    
    ObservableList<ProjectType> findAllProjectTypes() {
        LoggerFacade.getDefault().debug(this.getClass(), "ProjectTypeSqlService.findAllProjectTypes(): ObservableList<ProjectType>"); // NOI18N
        
        final ObservableList<ProjectType> allProjectTypes = FXCollections.observableArrayList();
        final List<ProjectType> projectTypes = DatabaseFacade.getDefault().getCrudService()
                .findByNamedQuery(ProjectType.class, NAMED_QUERY__NAME__FIND_ALL);
        
        allProjectTypes.addAll(projectTypes);
        Collections.sort(allProjectTypes);

        return allProjectTypes;
    }
    
    ObservableList<ProjectType> findAllProjectTypesWithName(final String name) {
        LoggerFacade.getDefault().debug(this.getClass(), "ProjectTypeSqlService.findAllProjectTypesWithName(String): ObservableList<ProjectType>"); // NOI18N
        
        final ObservableList<ProjectType> allProjectTypes = FXCollections.observableArrayList();
        final Map<String, Object> parameters = FXCollections.observableHashMap();
        parameters.put(PROJECTTYPE__COLUMN_NAME__NAME, name);
        
        final List<ProjectType> projectTypes = DatabaseFacade.getDefault().getCrudService()
                .findByNamedQuery(ProjectType.class, NAMED_QUERY__NAME__FIND_ALL_WITH_NAME, parameters);
        
        allProjectTypes.addAll(projectTypes);
        Collections.sort(allProjectTypes);

        return allProjectTypes;
    }

    void initialize() {
        LoggerFacade.getDefault().debug(this.getClass(), "ProjectTypeSqlService.initialize()"); // NOI18N
        
        final ObservableList<ProjectType> projectTypes = FXCollections.observableArrayList();
        projectTypes.addAll(this.findAllProjectTypesWithName(PROJECTTYPE__NAME_DEFECT));
        if (projectTypes.isEmpty()) {
            ProjectTypeSqlService.getDefault().create(new ProjectType(
                    System.nanoTime(), PROJECTTYPE__NAME_DEFECT, 
                    ColorConverter.convertToString(Color.CRIMSON)));
        }

        projectTypes.clear();
        projectTypes.addAll(this.findAllProjectTypesWithName(PROJECTTYPE__NAME_PROJECT));
        if (projectTypes.isEmpty()) {
            ProjectTypeSqlService.getDefault().create(new ProjectType(
                    System.nanoTime(), PROJECTTYPE__NAME_PROJECT, 
                    ColorConverter.convertToString(Color.AQUAMARINE)));
        }

        projectTypes.clear();
        projectTypes.addAll(this.findAllProjectTypesWithName(PROJECTTYPE__NAME_REQUIREMENT));
        if (projectTypes.isEmpty()) {
            ProjectTypeSqlService.getDefault().create(new ProjectType(
                    System.nanoTime(), PROJECTTYPE__NAME_REQUIREMENT, 
                    ColorConverter.convertToString(Color.CORNFLOWERBLUE)));
        }

        projectTypes.clear();
        projectTypes.addAll(this.findAllProjectTypesWithName(PROJECTTYPE__NAME_TEMPLATE));
        if (projectTypes.isEmpty()) {
            ProjectTypeSqlService.getDefault().create(new ProjectType(
                    System.nanoTime(), PROJECTTYPE__NAME_TEMPLATE, 
                    ColorConverter.convertToString(Color.DARKGOLDENROD)));
        }

        projectTypes.clear();
        projectTypes.addAll(this.findAllProjectTypesWithName(PROJECTTYPE__NAME_TASK));
        if (projectTypes.isEmpty()) {
            ProjectTypeSqlService.getDefault().create(new ProjectType(
                    System.nanoTime(), PROJECTTYPE__NAME_TASK, 
                    ColorConverter.convertToString(Color.GAINSBORO)));
        }
    }
    
    void update(final ProjectType projectType) {
        LoggerFacade.getDefault().debug(this.getClass(), "ProjectTypeSqlService.update(ProjectType)"); // NOI18N
        
        DatabaseFacade.getDefault().getCrudService().update(projectType);
    }
    
}
