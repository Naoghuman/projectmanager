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
package com.github.naoghuman.pm.model;

import com.github.naoghuman.lib.logger.core.LoggerFacade;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public final class ModelProvider {
    
    private static final Optional<ModelProvider> INSTANCE = Optional.of(new ModelProvider());

    public static final ModelProvider getDefault() {
        return INSTANCE.get();
    }
    
    private ModelProvider() {
        
    }
    
    public Board getBoard() {
        LoggerFacade.getDefault().debug(this.getClass(), "ModelProvider.getBoard()"); // NOI18N
        
        return new Board();
    }
    
    public Board getBoard(
            final long id,                      final long generationTime,
            final String name,                  final String description,
            final int index,                    final boolean favorite,
            final ArrayList<Long> employeerIds, final ArrayList<Long> tagIds,
            final ArrayList<Long> linkIds
    ) {
        LoggerFacade.getDefault().debug(this.getClass(), "ModelProvider.getBoard(" // NOI18N
                + "long, long, String, String, int, boolean, ArrayList<Long>, ArrayList<Long>, ArrayList<Long>)"); // NOI18N
        
        return new Board(
                id,           generationTime,
                name,         description,
                index,        favorite,
                employeerIds, tagIds,
                linkIds);
    }
    
    public Employeer getEmployeer() {
        LoggerFacade.getDefault().debug(this.getClass(), "ModelProvider.getEmployeer()"); // NOI18N
        
        return new Employeer();
    }
    
}
