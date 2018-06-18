/*
 * Copyright (C) 2018 PRo
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
import javafx.scene.paint.Color;

/**
 *
 * @author PRo
 */
public final class ColorConverter {
    
    private static final String SEMICOLON = ";"; // NOI18N
    
    public static Color convertToColor(final String color) throws IllegalArgumentException, NumberFormatException {
        LoggerFacade.getDefault().debug(ColorConverter.class, String.format(
                "ColorConverter.convertToColor(String=%s)", color)); // NOI18N
     
        PreConditionValidator.getDefault().requireNonNullAndNotEmpty(color);
        
        final String[] splitted = color.split(SEMICOLON);
        if (splitted.length != 4) {
            throw new IllegalArgumentException(String.format(
                    "The attribute [color=%s] don't match the format: rgba(0.0-1.0;0.0-1.0;0.0-1.0;0.0-1.0)", // NOI18N
                    color));
        }
        
        Color convertedColor = Color.TRANSPARENT;
        
        try {
            final double red     = Double.parseDouble(splitted[0]);
            final double green   = Double.parseDouble(splitted[1]);
            final double blue    = Double.parseDouble(splitted[2]);
            final double opacity = Double.parseDouble(splitted[3]);
            
            convertedColor = new Color(red, green, blue, opacity);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(String.format(
                    "The attribute [color=%s] can't be converted to a JavaFX color. It's don't match the format: rgba(0.0-1.0;0.0-1.0;0.0-1.0;0.0-1.0)", // NOI18N
                    color));
        }
        
        return convertedColor;
    }
    
    public static String convertToString(final Color color) {
        LoggerFacade.getDefault().debug(ColorConverter.class, String.format(
                "ColorConverter.convertToString(Color=%s)", color.toString())); // NOI18N
     
        PreConditionValidator.getDefault().requireNonNull(color);
        
        final StringBuilder sb = new StringBuilder();
        sb.append(color.getRed());
        sb.append(SEMICOLON);
        sb.append(color.getGreen());
        sb.append(SEMICOLON);
        sb.append(color.getBlue());
        sb.append(SEMICOLON);
        sb.append(color.getOpacity());
        
        return sb.toString();
    }
    
}
