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

import com.github.naoghuman.pm.configuration.DefaultConfiguration;
import com.github.naoghuman.pm.configuration.ProjectTypeConfiguration;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name = ProjectTypeConfiguration.ENTITY__TABLE_NAME__PROJECT_TYPE)
@NamedQueries({
    @NamedQuery(
            name  = ProjectTypeConfiguration.NAMED_QUERY__NAME__FIND_ALL,
            query = ProjectTypeConfiguration.NAMED_QUERY__QUERY__FIND_ALL),
    @NamedQuery(
            name  = ProjectTypeConfiguration.NAMED_QUERY__NAME__FIND_ALL_WITH_NAME,
            query = ProjectTypeConfiguration.NAMED_QUERY__QUERY__FIND_ALL_WITH_NAME)
})
public class ProjectType implements 
        Comparable<ProjectType>, Externalizable, 
        DefaultConfiguration, ProjectTypeConfiguration
{
    public ProjectType() {
        this(DEFAULT_ID, DEFAULT_STRING_EMPTY, DEFAULT_STRING_EMPTY);
    }
    
    public ProjectType(final long id, final String name, final String color) {
        this.init(id, name, color);
    }
    
    private void init(final long id, final String name, final String color) {
        this.setId(id);
        this.setName(name);
        this.setColor(color);
    }
    
    // START  ID ---------------------------------------------------------------
    private LongProperty idProperty;
    private long _id = DEFAULT_ID;

    @Id
    @Column(name = PROJECTTYPE__COLUMN_NAME__ID)
    public long getId() {
        if (idProperty == null) {
            return _id;
        } else {
            return idProperty.get();
        }
    }

    public final void setId(final long id) {
        if (idProperty == null) {
            _id = id;
        } else {
            idProperty.set(id);
        }
    }

    public LongProperty idProperty() {
        if (idProperty == null) {
            idProperty = new SimpleLongProperty(this, PROJECTTYPE__COLUMN_NAME__ID, _id);
        }
        
        return idProperty;
    }
    // END  ID -----------------------------------------------------------------
    
    // START  NAME -------------------------------------------------------------
    private StringProperty nameProperty = null;
    private String _name = DEFAULT_STRING_EMPTY;
    
    @Column(name = PROJECTTYPE__COLUMN_NAME__NAME)
    public String getName() {
        if (nameProperty == null) {
            return _name;
        } else {
            return nameProperty.get();
        }
    }
    
    public void setName(final String name) {
        if (nameProperty == null) {
            _name = name;
        } else {
            nameProperty.set(name);
        }
    }
    
    public StringProperty nameProperty() {
        if (nameProperty == null) {
            nameProperty = new SimpleStringProperty(this, PROJECTTYPE__COLUMN_NAME__NAME, _name);
        }
        
        return nameProperty;
    }
    // END  NAME ---------------------------------------------------------------
    
    // START  COLOR ------------------------------------------------------------
    private StringProperty colorProperty = null;
    private String _color = DEFAULT_STRING_EMPTY;
    
    @Column(name = PROJECTTYPE__COLUMN_NAME__COLOR)
    public String getColor() {
        if (colorProperty == null) {
            return _color;
        } else {
            return colorProperty.get();
        }
    }
    
    public void setColor(final String color) {
        if (colorProperty == null) {
            _color = color;
        } else {
            colorProperty.set(color);
        }
    }
    
    public StringProperty colorProperty() {
        if (colorProperty == null) {
            colorProperty = new SimpleStringProperty(this, PROJECTTYPE__COLUMN_NAME__COLOR, _color);
        }
        
        return colorProperty;
    }
    // END  COLOR --------------------------------------------------------------

    @Override
    public int compareTo(final ProjectType other) {
        return new CompareToBuilder()
                .append(this.getId(),    other.getId())
                .append(this.getName(),  other.getName())
                .append(this.getColor(), other.getColor())
                .toComparison();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (
                obj == null
                || this.getClass() != obj.getClass()
	) {
            return false;
        }
        
        final ProjectType other = (ProjectType) obj;
        return new EqualsBuilder()
                .append(this.getId(),    other.getId())
                .append(this.getName(),  other.getName())
                .append(this.getColor(), other.getColor())
                .isEquals();
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(this.getId())
                .append(this.getName())
                .append(this.getColor())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(PROJECTTYPE__COLUMN_NAME__ID,    this.getId())
                .append(PROJECTTYPE__COLUMN_NAME__NAME,  this.getName())
                .append(PROJECTTYPE__COLUMN_NAME__COLOR, this.getColor())
                .toString();
    }
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(this.getId());
        out.writeObject(this.getName());
        out.writeObject(this.getColor());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setId(in.readLong());
        this.setName(String.valueOf(in.readLong()));
        this.setColor(String.valueOf(in.readLong()));
    }
    
}
