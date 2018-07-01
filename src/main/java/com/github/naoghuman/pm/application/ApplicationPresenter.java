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
import com.github.naoghuman.pm.configuration.BoardConfiguration;
import static com.github.naoghuman.pm.configuration.DefaultConfiguration.DEFAULT_STRING_NEW;
import com.github.naoghuman.pm.configuration.NavigationConfiguration;
import com.github.naoghuman.pm.converter.NavigationConverter;
import com.github.naoghuman.pm.model.Board;
import com.github.naoghuman.pm.model.Employeer;
import com.github.naoghuman.pm.model.ModelProvider;
import com.github.naoghuman.pm.view.component.BoardButtonBuilder;
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
        BoardConfiguration, NavigationConfiguration, RegisterActions
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
        
        this.initializeDesktopArea();

        this.register();
    }
    
    public void initializeAfterWindowIsShowing() {
        LoggerFacade.getDefault().debug(this.getClass(), "ApplicationPresenter.initializeAfterWindowIsShowing()"); // NOI18N
    }
    
    private void initializeDesktopArea() {
        LoggerFacade.getDefault().debug(this.getClass(), "ApplicationPresenter.initializeDesktopArea()"); // NOI18N
      
        // Prepare the DesktopArea views
        this.onActionPrepareDesktopAreaViews(
                NAVIGATION__EMPTY, 
                DESKTOP_AREA__SHOW_VIEW_BOARD__FALSE, 
                DESKTOP_AREA__SHOW_VIEW_BOARDS__FALSE);
    }
    
    @Override
    public void register() {
        LoggerFacade.getDefault().debug(this.getClass(), "ApplicationPresenter.register()"); // NOI18N
        
        this.registerOnActionShowBoard();
        this.registerOnActionShowEmployeer();
    }
    
    private void registerOnActionShowBoard() {
        LoggerFacade.getDefault().debug(this.getClass(), "ApplicationPresenter.registerOnActionShowBoard()"); // NOI18N
        
        ActionHandlerFacade.getDefault().register(
                ON_ACTION__SHOW__BOARD,
                (ActionEvent event) -> {
                    final Object source = event.getSource();
                    if (source instanceof TransferData) {
                        final TransferData     transferData = (TransferData) source;
                        final Optional<Object> optional     = transferData.getObject();
                        if(optional.isPresent() && optional.get() instanceof Board) {
                            final Board board = (Board) optional.get();
                            this.onActionShowBoard(board);
                        }
                    }
                });
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
        
        // Prepare the DesktopArea views
        this.onActionPrepareDesktopAreaViews(
                NAVIGATION__BOARDS, 
                DESKTOP_AREA__SHOW_VIEW_BOARD__FALSE, 
                DESKTOP_AREA__SHOW_VIEW_BOARDS__TRUE);

        // Load content
        /*
        - load content for Favorites
        - add content to hbDesktopAreaBoardsFavorites
        
        - (v) add new-button to fpDesktopAreaBoardsBoards
        
        - load content for Boards
        - add content to fpDesktopAreaBoardsBoards
        
        */
        
        // Load all Boards for the section Favorites
        
        // Special new-button 
        final Board  board  = ModelProvider.getDefault().getBoard();
        final Button button = BoardButtonBuilder.getDefault().getButton(board);
        fpDesktopAreaBoardsBoards.getChildren().add(button);
        
        // Load all Boards for the section Boards
    }
    
    public void onActionClickNavigationEmployeers() {
        LoggerFacade.getDefault().debug(this.getClass(), "ApplicationPresenter.onActionClickNavigationEmployeers()"); // NOI18N
        
        // Prepare the DesktopArea views
        this.onActionPrepareDesktopAreaViews(
                NAVIGATION__EMPLOYEERS, 
                DESKTOP_AREA__SHOW_VIEW_BOARD__FALSE,
                DESKTOP_AREA__SHOW_VIEW_BOARDS__TRUE);

        // Load content
        /*
        - load content for Favorites
        - add content to hbDesktopAreaBoardsFavorites
        
        - (v) add new-button to fpDesktopAreaBoardsBoards
        
        - load content for Boards
        - add content to fpDesktopAreaBoardsBoards
        
        */
        
        // Load all Boards for the section Favorites
        
        // Special new-button 
        final Employeer employeer = ModelProvider.getDefault().getEmployeer();
        final Button    button    = BoardButtonBuilder.getDefault().getButton(employeer);
        fpDesktopAreaBoardsBoards.getChildren().add(button);
        
        // Load all Boards for the section Boards
    }
    
    private void onActionPrepareDesktopAreaViews(
            final String title, final boolean showDesktopAreaViewBoard,
            final boolean showDesktopAreaViewBoards
    ) {
        LoggerFacade.getDefault().debug(this.getClass(), "ApplicationPresenter.onActionPrepareDesktopAreaViews(String, boolean, boolean)"); // NOI18N
        
        // Show header text
        bDesktopAreaHeader.setText(title);
        
        // Tweak the layout
        vbDesktopAreaBoard.setVisible(showDesktopAreaViewBoard);
        vbDesktopAreaBoard.setManaged(showDesktopAreaViewBoard);
        
        vbDesktopAreaBoards.setVisible(showDesktopAreaViewBoards);
        vbDesktopAreaBoards.setManaged(showDesktopAreaViewBoards);
        
        // Cleanup previous content
        if (showDesktopAreaViewBoard) {
            vbDesktopAreaBoard.getChildren().clear();
        }
        
        if (showDesktopAreaViewBoards) {
            hbDesktopAreaBoardsFavorites.getChildren().clear();
            fpDesktopAreaBoardsBoards.getChildren().clear();
        }
    }
    
    private void onActionShowBoard(final Board board) {
        LoggerFacade.getDefault().debug(this.getClass(), "ApplicationPresenter.onActionShowBoard(Board)"); // NOI18N
        
        // Prepare the DesktopArea views
        this.onActionPrepareDesktopAreaViews(
                NavigationConverter.convert(NAVIGATION__BOARD, board.getName()),
                DESKTOP_AREA__SHOW_VIEW_BOARD__TRUE, 
                DESKTOP_AREA__SHOW_VIEW_BOARDS__FALSE);
        
        // Load content
    }

    private void onActionShowEmployeer(final Employeer employeer) {
        LoggerFacade.getDefault().debug(this.getClass(), "ApplicationPresenter.onnActionShowEmployeer(Employeer)"); // NOI18N
          
        // Prepare the DesktopArea views
        String name = employeer.getLastName();
        if (!name.equals(DEFAULT_STRING_NEW)) {
            name = String.format("%s, %s %s", // NOI18N
                    employeer.getLastName(), employeer.getFirstName(), 
                    employeer.getSecondName()); 
        }
        
        this.onActionPrepareDesktopAreaViews(
                NavigationConverter.convert(NAVIGATION__EMPLOYEER, name),
                DESKTOP_AREA__SHOW_VIEW_BOARD__TRUE, 
                DESKTOP_AREA__SHOW_VIEW_BOARDS__FALSE);
        
        // Load content
    }
    
}
