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
package com.github.naoghuman.pm.view;

import com.github.naoghuman.lib.action.core.ActionHandlerFacade;
import com.github.naoghuman.lib.action.core.TransferData;
import com.github.naoghuman.lib.action.core.TransferDataBuilder;
import com.github.naoghuman.lib.logger.core.LoggerFacade;
import com.github.naoghuman.pm.configuration.ActionConfiguration;
import static com.github.naoghuman.pm.configuration.DefaultConfiguration.DEFAULT_STRING_NEW;
import com.github.naoghuman.pm.model.BoardModel;
import com.github.naoghuman.pm.model.Employeer;
import com.github.naoghuman.pm.sql.SqlProvider;
import java.util.Optional;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public final class BoardButtonBuilder implements ActionConfiguration {
    
    private static final Optional<BoardButtonBuilder> INSTANCE = Optional.of(new BoardButtonBuilder());

    public static final BoardButtonBuilder getDefault() {
        return INSTANCE.get();
    }
    
    private BoardButtonBuilder() {
        
    }
    
    public Button getButton(final BoardModel board) {
        LoggerFacade.getDefault().debug(this.getClass(), "BoardButtonBuilder.getButton(Board)"); // NOI18N
        
        return this.getButton(board.getName(), board, ON_ACTION__SHOW__BOARD);
    }
    
    public Button getButton(final Employeer employeer) {
        LoggerFacade.getDefault().debug(this.getClass(), "BoardButtonBuilder.getButton(Employeer)"); // NOI18N
        
        String name = employeer.getLastName();
        if (!name.equals(DEFAULT_STRING_NEW)) {
            name = String.format("%s, %s %s", // NOI18N
                    employeer.getLastName(), employeer.getFirstName(), 
                    employeer.getSecondName()); 
        }
        
        return this.getButton(name, employeer, ON_ACTION__SHOW__EMPLOYEER);
    }
    
    private Button getButton(final String name, final Object userData, final String actionId) {
        LoggerFacade.getDefault().debug(this.getClass(), "BoardButtonBuilder.getButton(String, Object, String)"); // NOI18N
        
        final Button btn = new Button();
        btn.setPrefSize(256.0d, 128.0d);
        btn.setText(name);
        btn.setUserData(userData);
        
        btn.setOnAction(event -> {
            final TransferData transferData = TransferDataBuilder.create()
                    .actionId(actionId)
                    .objectValue(userData)
                    .build();
            ActionHandlerFacade.getDefault().handle(transferData);
        });
        
        return btn;
    }

    public ObservableList<HBox> getButtons(ObservableList<BoardModel> boards) {
        final ObservableList<HBox> buttons = FXCollections.observableArrayList();
        
        boards.stream()
                .map((board) -> {
                    final HBox hbox = new HBox();
                    final Button btn1 = this.getButton(board);
                    hbox.getChildren().add(btn1);
                    
                    final Button btn2 = new Button();
                    btn2.setText("(v)");
                    btn2.setOnAction((event) -> {
                        SqlProvider.getDefault().updateBoardFavorite(board);
                        
                        final PauseTransition pt = new PauseTransition();
                        pt.setDuration(Duration.millis(15));
                        pt.setCycleCount(1);
                        pt.setOnFinished((finished) -> {
                            ActionHandlerFacade.getDefault().handle(ON_ACTION__REFRESH__DESKTOP_AREA_BOARDS);
                        });
                        
                        pt.playFromStart();
                    });
                    hbox.getChildren().add(btn2);
                    
                    final Button btn3 = new Button();
                    btn3.setText("(x)");
                    btn3.setOnAction((event) -> {
                        SqlProvider.getDefault().deleteBoard(board);
                        
                        final PauseTransition pt = new PauseTransition();
                        pt.setDuration(Duration.millis(15));
                        pt.setCycleCount(1);
                        pt.setOnFinished((finished) -> {
                            ActionHandlerFacade.getDefault().handle(ON_ACTION__REFRESH__DESKTOP_AREA_BOARDS);
                        });
                        
                        pt.playFromStart();
                    });
                    hbox.getChildren().add(btn3);
                    
                    return hbox;
                }).forEachOrdered((hbox) -> {
                    buttons.add(hbox);
                });
        
        return buttons;
    }
    
}
