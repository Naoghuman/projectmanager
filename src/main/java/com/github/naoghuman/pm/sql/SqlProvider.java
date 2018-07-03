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
import com.github.naoghuman.pm.model.BoardModel;
import com.github.naoghuman.pm.model.Employeer;
import com.github.naoghuman.pm.model.Project;
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

    public void createBoard(BoardModel board) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        BoardSqlService.getDefault().createOrUpate(board);
        
        stopWatch.split();
        this.printToLog(stopWatch.toSplitString(), 1, "SqlProvider.createBoard(BoardModel)"); // NOI18N
        stopWatch.stop();
    }
    
    public void createEmployeer(final Employeer employeer) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        EmployeerSqlService.getDefault().create(employeer);
        
        stopWatch.split();
        this.printToLog(stopWatch.toSplitString(), 1, "SqlProvider.createEmployeer(Employeer)"); // NOI18N
        stopWatch.stop();
    }
    
    public void createProject(final Project project) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        ProjectSqlService.getDefault().create(project);
        
        stopWatch.split();
        this.printToLog(stopWatch.toSplitString(), 1, "SqlProvider.createProject(Project)"); // NOI18N
        stopWatch.stop();
    }
    
    public void createProjectType(final ProjectType projectType) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        ProjectTypeSqlService.getDefault().create(projectType);
        
        stopWatch.split();
        this.printToLog(stopWatch.toSplitString(), 1, "SqlProvider.createProjectType(ProjectType)"); // NOI18N
        stopWatch.stop();
    }

    public void deleteBoard(final BoardModel board) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        BoardSqlService.getDefault().delete(board);
        
        stopWatch.split();
        this.printToLog(stopWatch.toSplitString(), 1, "SqlProvider.deleteBoard(long)"); // NOI18N
        stopWatch.stop();
    }
    
    public ObservableList<BoardModel> findAllBoardsFavorites(final boolean favorites) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        final ObservableList<BoardModel> allFavorites = BoardSqlService.getDefault().findAllFavorites(favorites);
        
        stopWatch.split();
        this.printToLog(stopWatch.toSplitString(), allFavorites.size(), "SqlProvider.findAllBoardsFavorites(): ObservableList<BoardModel>"); // NOI18N
        stopWatch.stop();
        
        return allFavorites;
    }
    
    public ObservableList<Employeer> findAllEmployeers() {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        final ObservableList<Employeer> employeers = EmployeerSqlService.getDefault().findAllEmployeers();
        
        stopWatch.split();
        this.printToLog(stopWatch.toSplitString(), employeers.size(), "SqlProvider.findAllEmployeers(): ObservableList<Employeer>"); // NOI18N
        stopWatch.stop();
        
        return employeers;
    }
    
    public ObservableList<Employeer> findAllEmployeersWithName(final String name) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        final ObservableList<Employeer> employeers = EmployeerSqlService.getDefault().findAllEmployeersWithName(name);
        
        stopWatch.split();
        this.printToLog(stopWatch.toSplitString(), employeers.size(), "SqlProvider.findAllEmployeersWithName(String): ObservableList<Employeer>"); // NOI18N
        stopWatch.stop();
        
        return employeers;
    }
    
    public ObservableList<Project> findAllProjects() {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        final ObservableList<Project> projects = ProjectSqlService.getDefault().findAllProjects();
        
        stopWatch.split();
        this.printToLog(stopWatch.toSplitString(), projects.size(), "SqlProvider.findAllProjects(): ObservableList<Project>"); // NOI18N
        stopWatch.stop();
        
        return projects;
    }
    
    public ObservableList<Project> findAllProjectsWithName(final String name) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        final ObservableList<Project> projects = ProjectSqlService.getDefault().findAllProjectsWithName(name);
        
        stopWatch.split();
        this.printToLog(stopWatch.toSplitString(), projects.size(), "SqlProvider.findAllProjectsWithName(String): ObservableList<Project>"); // NOI18N
        stopWatch.stop();
        
        return projects;
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

    public void initializeTestData() {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        this.initializeTestDataEmployeers();
        this.initializeTestDataProjects();
        
        stopWatch.split();
        this.printToLog(stopWatch.toSplitString(), 1, "SqlProvider.initializeTestData()"); // NOI18N
        stopWatch.stop();
    }
    
    private void initializeTestDataEmployeers() {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        EmployeerSqlService.getDefault().initializeTestData();
        
        stopWatch.split();
        this.printToLog(stopWatch.toSplitString(), 1, "SqlProvider.initializeTestDataEmployeers()"); // NOI18N
        stopWatch.stop();
    }
    
    private void initializeTestDataProjects() {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        ProjectSqlService.getDefault().initializeTestData();
        
        stopWatch.split();
        this.printToLog(stopWatch.toSplitString(), 1, "SqlProvider.initializeTestDataProjects()"); // NOI18N
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
    
    public void updateBoardFavorite(final BoardModel board) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        BoardSqlService.getDefault().updateBoardFavorite(board);
        
        stopWatch.split();
        this.printToLog(stopWatch.toSplitString(), 1, "SqlProvider.updateBoardFavorite(BoardModel)"); // NOI18N
        stopWatch.stop();
    }
    
    public void updateEmployeer(final Employeer employeer) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        EmployeerSqlService.getDefault().update(employeer);
        
        stopWatch.split();
        this.printToLog(stopWatch.toSplitString(), 1, "SqlProvider.updateEmployeer(Employeer)"); // NOI18N
        stopWatch.stop();
    }
    
    public void updateProject(final Project project) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        ProjectSqlService.getDefault().update(project);
        
        stopWatch.split();
        this.printToLog(stopWatch.toSplitString(), 1, "SqlProvider.updateProject(Project)"); // NOI18N
        stopWatch.stop();
    }
    
    public void updateProjectType(final ProjectType projectType) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        ProjectTypeSqlService.getDefault().update(projectType);
        
        stopWatch.split();
        this.printToLog(stopWatch.toSplitString(), 1, "SqlProvider.updateProjectType(ProjectType)"); // NOI18N
        stopWatch.stop();
    }
    
}
