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

import com.github.naoghuman.lib.properties.core.PropertiesFacade;
import static com.github.naoghuman.pm.configuration.ApplicationConfiguration.KEY__APPLICATION__RESOURCE_BUNDLE;
import javafx.scene.paint.Color;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author PRo
 */
public class ColorConverterTest {
    
    public ColorConverterTest() {
    }
    
    @Before
    public void setUp() {
        PropertiesFacade.getDefault().register(KEY__APPLICATION__RESOURCE_BUNDLE);
    }
    
    @After
    public void tearDown() {
    }

    @Test(expected=NullPointerException.class)
    public void testConvertToColorThrowsNullPointerException() {
        String c = null;
        ColorConverter.convertToColor(c);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConvertToColorThrowsIllegalArgumentException() {
        String c = "";
        ColorConverter.convertToColor(c);
    }

    @Test
    public void testConvertToColor() {
        Color c = Color.RED;
        String strColor = c.getRed() + ";" + c.getGreen() + ";" + c.getBlue() + ";" + c.getOpacity();
        
        Color result = ColorConverter.convertToColor(strColor);
        
        assertEquals(result.getRed(),     Color.RED.getRed(),     0);
        assertEquals(result.getGreen(),   Color.RED.getGreen(),   0);
        assertEquals(result.getBlue(),    Color.RED.getBlue(),    0);
        assertEquals(result.getOpacity(), Color.RED.getOpacity(), 0);
    }

    @Test(expected=NullPointerException.class)
    public void testConvertToStringThrowsNullPointerException() {
        Color c = null;
        ColorConverter.convertToString(c);
    }

    @Test
    public void testConvertToString() {
        Color c = Color.RED;
        String expResult = c.getRed() + ";" + c.getGreen() + ";" + c.getBlue() + ";" + c.getOpacity();
        String result = ColorConverter.convertToString(c);
        
        assertEquals(expResult, result);
    }
    
}
