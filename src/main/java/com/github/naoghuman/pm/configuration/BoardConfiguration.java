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
package com.github.naoghuman.pm.configuration;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public interface BoardConfiguration {
    
    // Table
    public static final String ENTITY__TABLE_NAME__BOARD = "Board"; // NOI18N
    
    // Queries
    public static final String NAMED_QUERY__NAME__FIND_ALL_ARE_FAVORITE  = "Board.findAllAreFavorite"; // NOI18N
    public static final String NAMED_QUERY__QUERY__FIND_ALL_ARE_FAVORITE = "SELECT b FROM Board b WERE b.favorite == :favorite"; // NOI18N
    public static final String NAMED_QUERY__NAME__FIND_ALL_NOT_FAVORITE  = "Board.findAllNotFavorite"; // NOI18N
    public static final String NAMED_QUERY__QUERY__FIND_ALL_NOT_FAVORITE = "SELECT b FROM Board b WERE b.favorite != :favorite"; // NOI18N

    // Columns
    public static final String BOARD__COLUMN_NAME__DESCRIPTION     = "description"; // NOI18N
    public static final String BOARD__COLUMN_NAME__EMPLOYEER_IDS   = "employeerIds"; // NOI18N
    public static final String BOARD__COLUMN_NAME__FAVORITE        = "favorite"; // NOI18N
    public static final String BOARD__COLUMN_NAME__GENERATION_TIME = "generationTime"; // NOI18N
    public static final String BOARD__COLUMN_NAME__ID              = "id"; // NOI18N
    public static final String BOARD__COLUMN_NAME__INDEX           = "index"; // NOI18N
    public static final String BOARD__COLUMN_NAME__LINK_IDS        = "linkIds"; // NOI18N
    public static final String BOARD__COLUMN_NAME__NAME            = "name"; // NOI18N
    public static final String BOARD__COLUMN_NAME__TAG_IDS         = "tagIds"; // NOI18N
    
    // DesktopArea views
    public static final boolean DESKTOP_AREA__SHOW_VIEW_BOARD__FALSE  = false;
    public static final boolean DESKTOP_AREA__SHOW_VIEW_BOARD__TRUE   = true;
    public static final boolean DESKTOP_AREA__SHOW_VIEW_BOARDS__FALSE = false;
    public static final boolean DESKTOP_AREA__SHOW_VIEW_BOARDS__TRUE  = true;
    
}
