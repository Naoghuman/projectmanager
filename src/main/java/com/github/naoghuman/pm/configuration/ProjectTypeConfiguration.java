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
public interface ProjectTypeConfiguration {
    
    public static final String ENTITY__TABLE_NAME__PROJECT_TYPE = "ProjectType"; // NOI18N
    
    public static final String NAMED_QUERY__NAME__FIND_ALL            = "ProjectType.findAll"; // NOI18N
    public static final String NAMED_QUERY__QUERY__FIND_ALL           = "SELECT pt FROM ProjectType pt"; // NOI18N
    public static final String NAMED_QUERY__NAME__FIND_ALL_WITH_NAME  = "ProjectType.findAllWithName"; // NOI18N
    public static final String NAMED_QUERY__QUERY__FIND_ALL_WITH_NAME = "SELECT pt FROM ProjectType pt WHERE pt.name == :name"; // NOI18N
    
    public static final String PROJECTTYPE__COLUMN_NAME__COLOR = "color"; // NOI18N
    public static final String PROJECTTYPE__COLUMN_NAME__ID    = "id"; // NOI18N
    public static final String PROJECTTYPE__COLUMN_NAME__NAME  = "name"; // NOI18N
    
    public static final String PROJECTTYPE__NAME_DEFECT      = "Defect"; // NOI18N
    public static final String PROJECTTYPE__NAME_PROJECT     = "Project"; // NOI18N
    public static final String PROJECTTYPE__NAME_REQUIREMENT = "Requirement"; // NOI18N
    public static final String PROJECTTYPE__NAME_TASK        = "Task"; // NOI18N
    public static final String PROJECTTYPE__NAME_TEMPLATE    = "Template"; // NOI18N
    
}
