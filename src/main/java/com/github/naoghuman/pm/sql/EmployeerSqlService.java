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
import com.github.naoghuman.pm.configuration.DefaultConfiguration;
import com.github.naoghuman.pm.configuration.EmployeerConfiguration;
import com.github.naoghuman.pm.model.Employeer;
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
final class EmployeerSqlService implements DefaultConfiguration, EmployeerConfiguration {
    
    private static final Optional<EmployeerSqlService> INSTANCE = Optional.of(new EmployeerSqlService());

    public static final EmployeerSqlService getDefault() {
        return INSTANCE.get();
    }
    
    private EmployeerSqlService() {
        
    }
    
    void create(final Employeer employeer) {
        LoggerFacade.getDefault().debug(this.getClass(), "EmployeerSqlService.create(Employeer)"); // NOI18N
        
        if (Objects.equals(employeer.getId(), DEFAULT_ID)) {
            employeer.setId(System.currentTimeMillis());
            
            DatabaseFacade.getDefault().getCrudService().create(employeer);
        }
        else {
            this.update(employeer);
        }
    }

    void initializeTestData() {
        LoggerFacade.getDefault().info(this.getClass(), "EmployeerSqlService.initializeTestData()"); // NOI18N
        
        final String EMPLOYEER__TEST_DATA__EMPLOYEER_A = "Employeer A"; // NOI18N
        final String EMPLOYEER__TEST_DATA__EMPLOYEER_B = "Employeer B"; // NOI18N
        
        final ObservableList<Employeer> projects = FXCollections.observableArrayList();
        projects.addAll(this.findAllEmployeersWithName(EMPLOYEER__TEST_DATA__EMPLOYEER_A));
        if (projects.isEmpty()) {
            EmployeerSqlService.getDefault().create(new Employeer(
                    System.nanoTime(), System.nanoTime(),
                    EMPLOYEER__TEST_DATA__EMPLOYEER_A));
        }
        
        projects.clear();
        projects.addAll(this.findAllEmployeersWithName(EMPLOYEER__TEST_DATA__EMPLOYEER_B));
        if (projects.isEmpty()) {
            EmployeerSqlService.getDefault().create(new Employeer(
                    System.nanoTime(), System.nanoTime(),
                    EMPLOYEER__TEST_DATA__EMPLOYEER_B));
        }
    }
    
    ObservableList<Employeer> findAllEmployeers() {
        LoggerFacade.getDefault().debug(this.getClass(), "EmployeerSqlService.findAllEmployeers(): ObservableList<Employeer>"); // NOI18N
        
        final ObservableList<Employeer> allEmployeers = FXCollections.observableArrayList();
        final List<Employeer> employeers = DatabaseFacade.getDefault().getCrudService()
                .findByNamedQuery(Employeer.class, NAMED_QUERY__NAME__FIND_ALL);
        
        allEmployeers.addAll(employeers);
        Collections.sort(allEmployeers);

        return allEmployeers;
    }
    
    ObservableList<Employeer> findAllEmployeersWithName(final String name) {
        LoggerFacade.getDefault().debug(this.getClass(), "EmployeerSqlService.findAllEmployeersWithName(String): ObservableList<Employeer>"); // NOI18N
        
        final ObservableList<Employeer> allEmployeers = FXCollections.observableArrayList();
        final Map<String, Object> parameters = FXCollections.observableHashMap();
        parameters.put(EMPLOYEER__COLUMN_NAME__NAME, name);
        
        final List<Employeer> employeers = DatabaseFacade.getDefault().getCrudService()
                .findByNamedQuery(Employeer.class, NAMED_QUERY__NAME__FIND_ALL_WITH_NAME, parameters);
        
        allEmployeers.addAll(employeers);
        Collections.sort(allEmployeers);

        return allEmployeers;
    }
    
    void update(final Employeer employeer) {
        LoggerFacade.getDefault().debug(this.getClass(), "EmployeerSqlService.update(Employeer)"); // NOI18N
        
        DatabaseFacade.getDefault().getCrudService().update(employeer);
    }
    
}
