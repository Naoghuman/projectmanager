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
import static com.github.naoghuman.pm.configuration.DefaultConfiguration.DEFAULT_ID;
import static com.github.naoghuman.pm.configuration.DefaultConfiguration.SIGN__EMPTY;
import com.github.naoghuman.pm.configuration.EmployeerConfiguration;
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
@Table(name = EmployeerConfiguration.ENTITY__TABLE_NAME__EMPLOYEER)
@NamedQueries({
    @NamedQuery(
            name  = EmployeerConfiguration.NAMED_QUERY__NAME__FIND_ALL,
            query = EmployeerConfiguration.NAMED_QUERY__QUERY__FIND_ALL),
    @NamedQuery(
            name  = EmployeerConfiguration.NAMED_QUERY__NAME__FIND_ALL_WITH_NAME,
            query = EmployeerConfiguration.NAMED_QUERY__QUERY__FIND_ALL_WITH_NAME)
})
public class Employeer implements 
        Comparable<Employeer>, Externalizable, 
        DefaultConfiguration, EmployeerConfiguration
{
    public Employeer() {
        this(DEFAULT_ID, DEFAULT_ID, SIGN__EMPTY);
    }
    
    public Employeer(final long id, final long generationTime, final String name) {
        this.init(id, generationTime, name);
    }
    
    private void init(final long id, final long generationTime, final String name) {
        this.setId(id);
        this.setGenerationTime(generationTime);
        this.setName(name);
    }
    
    // START  ID ---------------------------------------------------------------
    private LongProperty idProperty;
    private long _id = DEFAULT_ID;

    @Id
    @Column(name = EMPLOYEER__COLUMN_NAME__ID)
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
            idProperty = new SimpleLongProperty(this, EMPLOYEER__COLUMN_NAME__ID, _id);
        }
        
        return idProperty;
    }
    // END  ID -----------------------------------------------------------------
    
    // START  GENERATION-TIME --------------------------------------------------
    private LongProperty generationTimeProperty;
    private long _generationTime = DEFAULT_ID;

    @Id
    @Column(name = EMPLOYEER__COLUMN_NAME__GENERATION_TIME)
    public long getGenerationTime() {
        if (generationTimeProperty == null) {
            return _generationTime;
        } else {
            return generationTimeProperty.get();
        }
    }

    public final void setGenerationTime(final long generationTime) {
        if (generationTimeProperty == null) {
            _generationTime = generationTime;
        } else {
            generationTimeProperty.set(generationTime);
        }
    }

    public LongProperty generationTimeProperty() {
        if (generationTimeProperty == null) {
            generationTimeProperty = new SimpleLongProperty(this, EMPLOYEER__COLUMN_NAME__GENERATION_TIME, _generationTime);
        }
        
        return generationTimeProperty;
    }
    // END  GENERATION-TIME ----------------------------------------------------
    
    // START  NAME -------------------------------------------------------------
    private StringProperty nameProperty = null;
    private String _name = SIGN__EMPTY;
    
    @Column(name = EMPLOYEER__COLUMN_NAME__NAME)
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
            nameProperty = new SimpleStringProperty(this, EMPLOYEER__COLUMN_NAME__NAME, _name);
        }
        
        return nameProperty;
    }
    // END  NAME ---------------------------------------------------------------

    @Override
    public int compareTo(final Employeer other) {
        return new CompareToBuilder()
                .append(other.getId(),             this.getId())
                .append(other.getGenerationTime(), this.getGenerationTime())
                .append(other.getName(),           this.getName())
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
        
        final Employeer other = (Employeer) obj;
        return new EqualsBuilder()
                .append(this.getId(),             other.getId())
                .append(this.getGenerationTime(), other.getGenerationTime())
                .append(this.getName(),           other.getName())
                .isEquals();
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(this.getId())
                .append(this.getGenerationTime())
                .append(this.getName())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(EMPLOYEER__COLUMN_NAME__ID,              this.getId())
                .append(EMPLOYEER__COLUMN_NAME__GENERATION_TIME, this.getGenerationTime())
                .append(EMPLOYEER__COLUMN_NAME__NAME,            this.getName())
                .toString();
    }
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(this.getId());
        out.writeLong(this.getGenerationTime());
        out.writeObject(this.getName());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setId(in.readLong());
        this.setGenerationTime(in.readLong());
        this.setName(String.valueOf(in.readLong()));
    }
    
}
