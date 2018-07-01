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
import com.github.naoghuman.pm.configuration.NavigationConfiguration;
import com.github.naoghuman.pm.model.Employeer;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public class ApplicationPresenter implements 
        Initializable, ActionConfiguration, ApplicationConfiguration, 
        NavigationConfiguration, RegisterActions
{
    @FXML private Button   bDesktopAreaHeader;
    @FXML private FlowPane fpDesktopAreaBoardsBoards;
    @FXML private HBox     hbDesktopAreaBoardsFavorites;
    @FXML private VBox     vbDesktopAreaBoard;
    @FXML private VBox     vbDesktopAreaBoards;
    
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
      
//        tbProjects.fire();
    }
    
    @Override
    public void register() {
        LoggerFacade.getDefault().debug(this.getClass(), "ApplicationPresenter.register()"); // NOI18N
        
        this.registerOnActionShowEmployeer();
    }
    
    private void registerOnActionShowEmployeer() {
        LoggerFacade.getDefault().debug(this.getClass(), "ApplicationPresenter.registerOnActionShowEmployeer()"); // NOI18N
        
        ActionHandlerFacade.getDefault().register(
                ON_ACTION__SHOW__EMPLOYEER,
                (ActionEvent event) -> {
                    final Object source = event.getSource();
                    if (source instanceof TransferData) {
                        final TransferData     transferData = (TransferData) source;
                        final Optional<Object> optional     = transferData.getObject();
                        if(optional.isPresent() && optional.get() instanceof Employeer) {
                            final Employeer employeer = (Employeer) optional.get();
                            this.onActionShowEmployeer(employeer);
                        }
                    }
                });
    }
    
    
    public void onActionClickNavigationBoards() {
        LoggerFacade.getDefault().debug(this.getClass(), "ApplicationPresenter.onActionClickNavigationBoards()"); // NOI18N
        
        // Set header
//        bHeader.setText(NAVIGATION__BOARDS);
    }
    
    public void onActionClickNavigationEmployeers() {
        LoggerFacade.getDefault().debug(this.getClass(), "ApplicationPresenter.onActionClickNavigationEmployeers()"); // NOI18N
        
        // Set header
//        bDesktopAreaHeader.setText(NAVIGATION__EMPLOYEER);
        
//        // Clear the content
//        fpMainArea.getChildren().clear();
//        
//        // Load new content
//        final ObservableList<Employeer> employeers = SqlProvider.getDefault().findAllEmployeers();
//        employeers.stream()
//                .forEach(employeer -> {
//                    final Button btn = ButtonBuilder.getDefault().getButton(employeer);
//                    fpMainArea.getChildren().add(btn);
//                });
    }

    private void onActionShowEmployeer(final Employeer employeer) {
        LoggerFacade.getDefault().debug(this.getClass(), "ApplicationPresenter.onnActionShowEmployeer(Employeer)"); // NOI18N
          
        // Set header
        final String fullName = String.format("%s, %s %s", // NOI18N
                employeer.getLinkIds(), employeer.getFirstName(), employeer.getSecondName());
        bDesktopAreaHeader.setText(String.format("Employeer: %s", fullName)); // NOI18N
        
//        // Clear the content
//        tbEmployeers.setSelected(false);
//        fpMainArea.getChildren().clear();
    }
    
}
