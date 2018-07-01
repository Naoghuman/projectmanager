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
public interface EmployeerConfiguration {
    
    // Table
    public static final String ENTITY__TABLE_NAME__EMPLOYEER = "Employeer"; // NOI18N
    
    // Queries
    public static final String NAMED_QUERY__NAME__FIND_ALL_ARE_FAVORITE  = "Employeer.findAllAreFavorite"; // NOI18N
    public static final String NAMED_QUERY__QUERY__FIND_ALL_ARE_FAVORITE = "SELECT e FROM Employeer e WERE e.favorite == :favorite"; // NOI18N
    public static final String NAMED_QUERY__NAME__FIND_ALL_NOT_FAVORITE  = "Employeer.findAllNotFavorite"; // NOI18N
    public static final String NAMED_QUERY__QUERY__FIND_ALL_NOT_FAVORITE = "SELECT e FROM Employeer e WERE e.favorite != :favorite"; // NOI18N

    // Columns
    public static final String EMPLOYEER__COLUMN_NAME__EMAILS           = "emails"; // NOI18N
    public static final String EMPLOYEER__COLUMN_NAME__EMPLOYEER_IDS    = "employeerIds"; // NOI18N
    public static final String EMPLOYEER__COLUMN_NAME__FAVORITE         = "favorite"; // NOI18N
    public static final String EMPLOYEER__COLUMN_NAME__FIRST_NAME       = "firstName"; // NOI18N
    public static final String EMPLOYEER__COLUMN_NAME__GENERATION_TIME  = "generationTime"; // NOI18N
    public static final String EMPLOYEER__COLUMN_NAME__ICON             = "icon"; // NOI18N
    public static final String EMPLOYEER__COLUMN_NAME__ID               = "id"; // NOI18N
    public static final String EMPLOYEER__COLUMN_NAME__INDEX            = "index"; // NOI18N
    public static final String EMPLOYEER__COLUMN_NAME__LAST_NAME        = "lastName"; // NOI18N
    public static final String EMPLOYEER__COLUMN_NAME__LINK_IDS         = "linkIds"; // NOI18N
    public static final String EMPLOYEER__COLUMN_NAME__OFFICE           = "office"; // NOI18N
    public static final String EMPLOYEER__COLUMN_NAME__PHONES           = "phones"; // NOI18N
    public static final String EMPLOYEER__COLUMN_NAME__RESPONSIBILITIES = "responsibilities"; // NOI18N
    public static final String EMPLOYEER__COLUMN_NAME__SECOND_NAME      = "secondName"; // NOI18N
    public static final String EMPLOYEER__COLUMN_NAME__TAG_IDS          = "tagIds"; // NOI18N
    public static final String EMPLOYEER__COLUMN_NAME__TITLES           = "titles"; // NOI18N
    
}
