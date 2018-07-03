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
package com.github.naoghuman.pm.view.board;

import com.github.naoghuman.lib.logger.core.LoggerFacade;
import com.github.naoghuman.pm.configuration.DefaultConfiguration;
import com.github.naoghuman.pm.model.BoardModel;
import com.github.naoghuman.pm.sql.SqlProvider;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * TODO
 *  - First section is a always BoardList("Details"). Can't be deleted.
 *  - Can be hided. Will then shown as a button on the left.
 *  - User can add, remove, change in order xy BoardLists. (look in Trello how that work).
 *    - In Trello its on the always right side a 'add new list' section.
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public class BoardPresenter implements Initializable, DefaultConfiguration {
    
    @FXML private HBox hbBoard;

    private TextField tfLastName;
    
    private BoardModel board;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "BoardPresenter.initialize(URL, ResourceBundle)"); // NOI18N
        
    }
    
    public void configure(final BoardModel board) {
        LoggerFacade.getDefault().debug(this.getClass(), "BoardPresenter.configure(BoardModel)"); // NOI18N
        
        this.board = board;
        
        /*
        vbox
         - button save
         - button delete
         - textfield name
        */
        // XXX
        final VBox vbox = new VBox();
        vbox.setStyle("-fx-background-color:red;");
        
        tfLastName = new TextField(board.getName());
        vbox.getChildren().add(tfLastName);
        
        final Button save = new Button("Save");
        save.setOnAction((event) -> { this.save(); });
        vbox.getChildren().add(save);
        
        final Button delete = new Button("Delete");
        delete.setOnAction((event) -> { this.delete(); });
        vbox.getChildren().add(delete);
        
        hbBoard.getChildren().add(vbox);
    }
    
    private void save() {
        LoggerFacade.getDefault().debug(this.getClass(), "BoardPresenter.save()"); // NOI18N
        
        board.setName(tfLastName.getText());
        
        SqlProvider.getDefault().createBoard(board);
    }
    
    private void delete() {
        LoggerFacade.getDefault().debug(this.getClass(), "BoardPresenter.delete()"); // NOI18N
        
    }
    
}
