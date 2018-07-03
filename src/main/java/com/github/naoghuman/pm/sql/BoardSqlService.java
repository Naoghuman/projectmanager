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
import com.github.naoghuman.pm.configuration.BoardConfiguration;
import com.github.naoghuman.pm.configuration.DefaultConfiguration;
import com.github.naoghuman.pm.model.BoardModel;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
final class BoardSqlService implements DefaultConfiguration, BoardConfiguration {
    
    private static final Optional<BoardSqlService> INSTANCE = Optional.of(new BoardSqlService());

    public static final BoardSqlService getDefault() {
        return INSTANCE.get();
    }
    
    private BoardSqlService() {
        
    }
    
    void createOrUpate(final BoardModel board) {
        LoggerFacade.getDefault().debug(this.getClass(), "BoardSqlService.createOrUpate(BoardModel)"); // NOI18N
        
        if (Objects.equals(board.getId(), DEFAULT_ID)) {
            board.setId(System.nanoTime());
            board.setGenerationTime(System.nanoTime());
            
            DatabaseFacade.getDefault().getCrudService().create(board);
        }
        else {
            this.update(board);
        }
    }

    void initializeTestData() {
        LoggerFacade.getDefault().info(this.getClass(), "BoardSqlService.initializeTestData()"); // NOI18N
        
//        final String EMPLOYEER__TEST_DATA__EMPLOYEER_A = "Employeer A"; // NOI18N
//        final String EMPLOYEER__TEST_DATA__EMPLOYEER_B = "Employeer B"; // NOI18N
//        
//        final ObservableList<Employeer> projects = FXCollections.observableArrayList();
//        projects.addAll(this.findAllEmployeersWithName(EMPLOYEER__TEST_DATA__EMPLOYEER_A));
//        if (projects.isEmpty()) {
//            EmployeerSqlService.getDefault().create(new Employeer(
//                    System.nanoTime(), System.nanoTime(),
//                    EMPLOYEER__TEST_DATA__EMPLOYEER_A));
//        }
//        
//        projects.clear();
//        projects.addAll(this.findAllEmployeersWithName(EMPLOYEER__TEST_DATA__EMPLOYEER_B));
//        if (projects.isEmpty()) {
//            EmployeerSqlService.getDefault().create(new Employeer(
//                    System.nanoTime(), System.nanoTime(),
//                    EMPLOYEER__TEST_DATA__EMPLOYEER_B));
//        }
    }
    
    ObservableList<BoardModel> findAllFavorites(final boolean favorite) {
        LoggerFacade.getDefault().debug(this.getClass(), "BoardSqlService.findAllFavorites(boolean): ObservableList<BoardModel>"); // NOI18N
        
        final ObservableList<BoardModel> allBoards = FXCollections.observableArrayList();
        final Map<String, Object> parameters = FXCollections.observableHashMap();
        parameters.put(BOARD__COLUMN_NAME__FAVORITE, favorite);
        
        final List<BoardModel> boards = DatabaseFacade.getDefault().getCrudService()
                .findByNamedQuery(BoardModel.class, NAMED_QUERY__NAME__FIND_ALL__FAVORITES, parameters);
        
        allBoards.addAll(boards);
        Collections.sort(allBoards);

        return allBoards;
    }

    void delete(final BoardModel board) {
        LoggerFacade.getDefault().debug(this.getClass(), "BoardSqlService.delete(long)"); // NOI18N
        
        DatabaseFacade.getDefault().getCrudService().beginTransaction();
        DatabaseFacade.getDefault().getCrudService().getEntityManager().remove(board);
        DatabaseFacade.getDefault().getCrudService().commitTransaction();
    }
    
    void update(final BoardModel board) {
        LoggerFacade.getDefault().debug(this.getClass(), "BoardSqlService.update(BoardModel)"); // NOI18N
        
        DatabaseFacade.getDefault().getCrudService().update(board);
    }

    void updateBoardFavorite(BoardModel board) {
        LoggerFacade.getDefault().debug(this.getClass(), "BoardSqlService.updateBoardFavorite(BoardModel)"); // NOI18N
        
        board.setFavorite(!board.getFavorite());
        this.createOrUpate(board);
    }
    
}
