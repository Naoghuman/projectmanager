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
package com.github.naoghuman.pm.search;

import com.github.naoghuman.lib.logger.core.LoggerFacade;
import java.util.Optional;
import javafx.scene.control.ToggleButton;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public final class SearchProvider {
    
    private static final Optional<SearchProvider> INSTANCE = Optional.of(new SearchProvider());

    public static final SearchProvider getDefault() {
        return INSTANCE.get();
    }
    
    private SearchProvider() {
        
    }
    
    public void onActionClickSearch(Optional<ToggleButton> optional) {
        LoggerFacade.getDefault().debug(this.getClass(), "SearchProvider.onActionClickSearch(Optional<ToggleButton>)"); // NOI18N
        
        
    }
    
}
