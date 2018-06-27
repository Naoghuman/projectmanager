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
package com.github.naoghuman.pm.application;

import com.github.naoghuman.lib.action.core.ActionHandlerFacade;
import com.github.naoghuman.lib.action.core.RegisterActions;
import com.github.naoghuman.lib.action.core.TransferData;
import com.github.naoghuman.lib.logger.core.LoggerFacade;
import com.github.naoghuman.pm.configuration.ActionConfiguration;
import com.github.naoghuman.pm.configuration.ApplicationConfiguration;
import com.github.naoghuman.pm.model.Project;
import com.github.naoghuman.pm.model.ProjectType;
import com.github.naoghuman.pm.sql.SqlProvider;
import com.github.naoghuman.pm.view.component.ButtonBuilder;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public class ApplicationPresenter implements Initializable, ActionConfiguration, ApplicationConfiguration, RegisterActions {

    @FXML private Button       bHeader;
    @FXML private FlowPane     fpMainArea;
    @FXML private ToggleButton tbEmployeer;
    @FXML private ToggleButton tbProjectTypes;
    @FXML private ToggleButton tbProjects;
    @FXML private ToggleButton tbTags;
    @FXML private ToggleGroup  toggleGroup;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "ApplicationPresenter.initialize(URL, ResourceBundle)"); // NOI18N
        
//        assert (apView != null) : "fx:id=\"apView\" was not injected: check your FXML file 'application.fxml'."; // NOI18N
        
        this.initializeToggleButton();

        this.register();
    }
    
    public void initializeAfterWindowIsShowing() {
        LoggerFacade.getDefault().debug(this.getClass(), "ApplicationPresenter.initializeAfterWindowIsShowing()"); // NOI18N
    }
    
    private void initializeToggleButton() {
        LoggerFacade.getDefault().debug(this.getClass(), "ApplicationPresenter.initializeAfterWindowIsShowing()"); // NOI18N
      
        tbProjects.fire();
    }
    
    @Override
    public void register() {
        LoggerFacade.getDefault().debug(this.getClass(), "ApplicationPresenter.register()"); // NOI18N
        
        this.registerOnActionShowProjectType();
    }
    
    private void registerOnActionShowProjectType() {
        LoggerFacade.getDefault().debug(this.getClass(), "ApplicationPresenter.registerOnActionShowProjectType()"); // NOI18N
        
        ActionHandlerFacade.getDefault().register(
                ON_ACTION__SHOW__PROJECT_TYPE,
                (ActionEvent event) -> {
                    final Object source = event.getSource();
                    if (source instanceof TransferData) {
                        final TransferData     transferData = (TransferData) source;
                        final Optional<Object> optional     = transferData.getObject();
                        if(optional.isPresent() && optional.get() instanceof ProjectType) {
                            final ProjectType projectType = (ProjectType) optional.get();
                            this.onActionShowProjectType(projectType);
                        }
                    }
                });
    }
    
    public void onActionClickNew() {
        LoggerFacade.getDefault().debug(this.getClass(), "ApplicationPresenter.onActionClickNew()"); // NOI18N
       
//        final Optional<ToggleButton> toggleButton = this.onActionGetActiveMainPage();
//       
//        Button b = new Button("Project");
//        b.setMinSize(256.0d, 96.0d);
//       
//        fpProjects.getChildren().add(b);
//       
//        final ObservableList<ProjectType> projectTypes = SqlProvider.getDefault().findAllProjectTypes();
//        final ProjectTypeView      view      = new ProjectTypeView();
//        final ProjectTypePresenter presenter = view.getRealPresenter();
//        presenter.configure(projectTypes.get(0));
//
//        fpProjects.getChildren().add(view.getView());
    }
    
    public void onActionClickSearch() {
        LoggerFacade.getDefault().debug(this.getClass(), "ApplicationPresenter.onActionClickNew()"); // NOI18N
        
//        final Optional<ToggleButton> toggleButton = this.onActionGetActiveMainPage();
//        SearchProvider.getDefault().onActionClickSearch(toggleButton);
    }
    
    public void onActionClickToggleButtonEmployeer() {
        LoggerFacade.getDefault().debug(this.getClass(), "ApplicationPresenter.onActionClickToggleButtonEmployeer()"); // NOI18N
        
        // Set header
        bHeader.setText(MAIN_PAGE__EMPLOYEER);
        
        // Clear the content
        fpMainArea.getChildren().clear();
        
    }
    
    public void onActionClickToggleButtonProjects() {
        LoggerFacade.getDefault().debug(this.getClass(), "ApplicationPresenter.onActionClickToggleButtonProjects()"); // NOI18N
          
        // Set header
        bHeader.setText(MAIN_PAGE__PROJECTS);
        
        // Clear the content
        fpMainArea.getChildren().clear();
        
        // Load new content
        final ObservableList<Project> projects = SqlProvider.getDefault().findAllProjects();
        projects.stream()
                .forEach(project -> {
                    final Button btn = ButtonBuilder.getDefault().getButton(project);
                    fpMainArea.getChildren().add(btn);
                });
    }
    
    public void onActionClickToggleButtonProjectTypes() {
        LoggerFacade.getDefault().debug(this.getClass(), "ApplicationPresenter.onActionClickToggleButtonProjectTypes()"); // NOI18N
          
        // Set header
        bHeader.setText(MAIN_PAGE__PROJECT_TYPES);
        
        // Clear the content
        fpMainArea.getChildren().clear();
        
        // Load new content
        final ObservableList<ProjectType> projectTypes = SqlProvider.getDefault().findAllProjectTypes();
        projectTypes.stream()
                .forEach(projectType -> {
                    final Button btn = ButtonBuilder.getDefault().getButton(projectType);
                    fpMainArea.getChildren().add(btn);
                });
    }
    
    public void onActionClickToggleButtonTags() {
        LoggerFacade.getDefault().debug(this.getClass(), "ApplicationPresenter.onActionClickToggleButtonTags()"); // NOI18N
          
        // Set header
        bHeader.setText(MAIN_PAGE__PROJECTS);
        
        // Clear the content
        fpMainArea.getChildren().clear();
        
    }

    private void onActionShowProjectType(final ProjectType projectType) {
        LoggerFacade.getDefault().debug(this.getClass(), "ApplicationPresenter.onnActionShowProjectType(ProjectType)"); // NOI18N
          
        // Set header
        bHeader.setText("Projecttype: " + projectType.getName()); // NOI18N
        
        // Clear the content
        tbProjectTypes.setSelected(false);
        fpMainArea.getChildren().clear();
    }
    
}
