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

import com.github.naoghuman.lib.logger.core.LoggerFacade;
import com.github.naoghuman.pm.model.ProjectType;
import java.util.Optional;
import javafx.collections.ObservableList;
import org.apache.commons.lang3.time.StopWatch;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public final class SqlProvider {
    
    private static final Optional<SqlProvider> INSTANCE = Optional.of(new SqlProvider());

    public static final SqlProvider getDefault() {
        return INSTANCE.get();
    }
    
    private SqlProvider() {
        
    }
    
    public void createProjectType(final ProjectType projectType) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        ProjectTypeSqlService.getDefault().create(projectType);
        
        stopWatch.split();
        this.printToLog(stopWatch.toSplitString(), 1, "SqlProvider.createProjectType(ProjectType)"); // NOI18N
        stopWatch.stop();
    }
    
    public ObservableList<ProjectType> findAllProjectTypes() {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        final ObservableList<ProjectType> projectTypes = ProjectTypeSqlService.getDefault().findAllProjectTypes();
        
        stopWatch.split();
        this.printToLog(stopWatch.toSplitString(), projectTypes.size(), "SqlProvider.findAllProjectTypes(): ObservableList<ProjectType>"); // NOI18N
        stopWatch.stop();
        
        return projectTypes;
    }
    
    public ObservableList<ProjectType> findAllProjectTypesWithName(final String name) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        final ObservableList<ProjectType> projectTypes = ProjectTypeSqlService.getDefault().findAllProjectTypesWithName(name);
        
        stopWatch.split();
        this.printToLog(stopWatch.toSplitString(), projectTypes.size(), "SqlProvider.findAllProjectTypesWithName(String): ObservableList<ProjectType>"); // NOI18N
        stopWatch.stop();
        
        return projectTypes;
    }
    
    public void initialize() {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        this.initializeProjectTypes();
        
        stopWatch.split();
        this.printToLog(stopWatch.toSplitString(), 1, "SqlProvider.initialize()"); // NOI18N
        stopWatch.stop();
    }
    
    private void initializeProjectTypes() {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        ProjectTypeSqlService.getDefault().initialize();
        
        stopWatch.split();
        this.printToLog(stopWatch.toSplitString(), 1, "SqlProvider.initializeProjectTypes()"); // NOI18N
        stopWatch.stop();
    }
    
    private void printToLog(final String split, final int entities, final String method) {
        final StringBuilder sb = new StringBuilder();
        sb.append("  + Need "); // NOI18N
        sb.append(split);
        sb.append(" for [");
        sb.append(entities);
        sb.append("] entities in [");
        sb.append(method);
        sb.append("]");
        
        LoggerFacade.getDefault().debug(this.getClass(), sb.toString());
    }
    
    public void updateProjectType(final ProjectType prototype) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        ProjectTypeSqlService.getDefault().update(prototype);
        
        stopWatch.split();
        this.printToLog(stopWatch.toSplitString(), 1, "SqlProvider.updateProjectType(ProjectType)"); // NOI18N
        stopWatch.stop();
    }
    
}
