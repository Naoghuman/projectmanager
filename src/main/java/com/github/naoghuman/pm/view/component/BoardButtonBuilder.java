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
package com.github.naoghuman.pm.view.component;

import com.github.naoghuman.lib.action.core.ActionHandlerFacade;
import com.github.naoghuman.lib.action.core.TransferData;
import com.github.naoghuman.lib.action.core.TransferDataBuilder;
import com.github.naoghuman.lib.logger.core.LoggerFacade;
import com.github.naoghuman.pm.configuration.ActionConfiguration;
import com.github.naoghuman.pm.model.Board;
import com.github.naoghuman.pm.model.Employeer;
import com.github.naoghuman.pm.model.Project;
import com.github.naoghuman.pm.model.ProjectType;
import java.util.Optional;
import javafx.scene.control.Button;

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
    
    public Button getButton(final Board board) {
        LoggerFacade.getDefault().debug(this.getClass(), "BoardButtonBuilder.getButton(Board)"); // NOI18N
        
        return this.getButton(board.getName(), board, ON_ACTION__SHOW__BOARD);
    }
    
    public Button getButton(final Employeer employeer) {
        LoggerFacade.getDefault().debug(this.getClass(), "BoardButtonBuilder.getButton(Employeer)"); // NOI18N
        
        final String fullName = String.format("%s, %s %s", // NOI18N
                employeer.getLinkIds(), employeer.getFirstName(), employeer.getSecondName());
        
        return this.getButton(fullName, employeer, ON_ACTION__SHOW__EMPLOYEER);
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
    
//    public Button getButton(final Project project) {
//        LoggerFacade.getDefault().debug(this.getClass(), "BoardButtonBuilder.getButton(Project)"); // NOI18N
//
//        return this.getButton(project.getName(), project, ON_ACTION__SHOW__PROJECT);
//    }
    
//    public Button getButton(final ProjectType projectType) {
//        LoggerFacade.getDefault().debug(this.getClass(), "BoardButtonBuilder.getButton(ProjectType)"); // NOI18N
//
//        return this.getButton(projectType.getName(), projectType, ON_ACTION__SHOW__PROJECT_TYPE);
//    }
    
}
