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
package com.github.naoghuman.pm.converter;

import com.github.naoghuman.lib.logger.core.LoggerFacade;
import com.github.naoghuman.lib.validation.core.validator.PreConditionValidator;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public final class NavigationConverter {
    
    public static String convert(final String navigation, final String name) {
        LoggerFacade.getDefault().debug(NavigationConverter.class, "NavigationConverter.convert(String, String)"); // NOI18N
     
        PreConditionValidator.getDefault().requireNonNullAndNotEmpty(navigation);
        PreConditionValidator.getDefault().requireNonNullAndNotEmpty(name);
        
        return String.format("%s: %s", navigation, name);
    }
    
}
