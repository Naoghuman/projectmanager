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

import com.github.naoghuman.pm.configuration.BoardConfiguration;
import com.github.naoghuman.pm.configuration.DefaultConfiguration;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
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
@Table(name = BoardConfiguration.ENTITY__TABLE_NAME__BOARD)
@NamedQueries({
    @NamedQuery(
            name  = BoardConfiguration.NAMED_QUERY__NAME__FIND_ALL_ARE_FAVORITE,
            query = BoardConfiguration.NAMED_QUERY__QUERY__FIND_ALL_ARE_FAVORITE),
    @NamedQuery(
            name  = BoardConfiguration.NAMED_QUERY__NAME__FIND_ALL_NOT_FAVORITE,
            query = BoardConfiguration.NAMED_QUERY__QUERY__FIND_ALL_NOT_FAVORITE)
})
public class Board implements 
        Comparable<Board>, Externalizable, 
        DefaultConfiguration, BoardConfiguration
{
    public Board() {
        this(
                DEFAULT_ID, DEFAULT_ID,
                DEFAULT_STRING, DEFAULT_STRING,
                0, false,
                new ArrayList<Long>(), new ArrayList<Long>(),
                new ArrayList<Long>());
    }
    
    public Board(
            final long id, final long generationTime,
            final String name, final String description,
            final int index, final boolean favorite,
            final ArrayList<Long> employeerIds, final ArrayList<Long> tagIds,
            final ArrayList<Long> linkIds
    ) {
        this.init(
                id, generationTime,
                name, description,
                index, favorite,
                employeerIds, tagIds,
                linkIds);
    }
    
    private void init(
            final long id, final long generationTime,
            final String name, final String description,
            final int index, final boolean favorite,
            final ArrayList<Long> employeerIds, final ArrayList<Long> tagIds,
            final ArrayList<Long> linkIds
    ) {
        this.setId(id);
        this.setGenerationTime(generationTime);
        this.setName(name);
        this.setDescription(description);
        this.setIndex(index);
        this.setFavorite(favorite);
//        this.setEmployeerIds(employeerIds);
//        this.setTagIds(tagIds);
//        this.setLinkIds(linkIds);
    }
    
    // START  ID ---------------------------------------------------------------
    private LongProperty idProperty;
    private long _id = DEFAULT_ID;

    @Id
    @Column(name = BOARD__COLUMN_NAME__ID)
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
            idProperty = new SimpleLongProperty(this, BOARD__COLUMN_NAME__ID, _id);
        }
        
        return idProperty;
    }
    // END  ID -----------------------------------------------------------------
    
    // START  GENERATION-TIME --------------------------------------------------
    private LongProperty generationTimeProperty;
    private long _generationTime = DEFAULT_ID;

    @Id
    @Column(name = BOARD__COLUMN_NAME__GENERATION_TIME)
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
            generationTimeProperty = new SimpleLongProperty(this, BOARD__COLUMN_NAME__GENERATION_TIME, _generationTime);
        }
        
        return generationTimeProperty;
    }
    // END  GENERATION-TIME ----------------------------------------------------
    
    // START  NAME -------------------------------------------------------------
    private StringProperty nameProperty = null;
    private String _name = DEFAULT_STRING;
    
    @Column(name = BOARD__COLUMN_NAME__NAME)
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
            nameProperty = new SimpleStringProperty(this, BOARD__COLUMN_NAME__NAME, _name);
        }
        
        return nameProperty;
    }
    // END  NAME ---------------------------------------------------------------
    
    // START  DESCRIPTION ------------------------------------------------------
    private StringProperty descriptionProperty = null;
    private String _description = DEFAULT_STRING;
    
    @Column(name = BOARD__COLUMN_NAME__DESCRIPTION)
    public String getDescription() {
        if (descriptionProperty == null) {
            return _description;
        } else {
            return descriptionProperty.get();
        }
    }
    
    public void setDescription(final String description) {
        if (descriptionProperty == null) {
            _description = description;
        } else {
            descriptionProperty.set(description);
        }
    }
    
    public StringProperty descriptionProperty() {
        if (descriptionProperty == null) {
            descriptionProperty = new SimpleStringProperty(this, BOARD__COLUMN_NAME__DESCRIPTION, _description);
        }
        
        return descriptionProperty;
    }
    // END  DESCRIPTION --------------------------------------------------------
    
    // START  INDEX ------------------------------------------------------------
    private IntegerProperty indexProperty = null;
    private int _index = DEFAULT_INDEX;
    
    @Column(name = BOARD__COLUMN_NAME__NAME)
    public int getIndex() {
        if (indexProperty == null) {
            return _index;
        } else {
            return indexProperty.get();
        }
    }
    
    public void setIndex(final int index) {
        if (indexProperty == null) {
            _index = index;
        } else {
            indexProperty.set(index);
        }
    }
    
    public IntegerProperty indexProperty() {
        if (indexProperty == null) {
            indexProperty = new SimpleIntegerProperty(this, BOARD__COLUMN_NAME__NAME, _index);
        }
        
        return indexProperty;
    }
    // END  INDEX --------------------------------------------------------------
    
    // START  FAVORITE ---------------------------------------------------------
    private BooleanProperty favoriteProperty = null;
    private boolean _favorite = DEFAULT_BOOLEAN;
    
    @Column(name = BOARD__COLUMN_NAME__FAVORITE)
    public boolean getFavorite() {
        if (favoriteProperty == null) {
            return _favorite;
        } else {
            return favoriteProperty.get();
        }
    }
    
    public void setFavorite(final boolean favorite) {
        if (favoriteProperty == null) {
            _favorite = favorite;
        } else {
            favoriteProperty.set(favorite);
        }
    }
    
    public BooleanProperty favoriteProperty() {
        if (favoriteProperty == null) {
            favoriteProperty = new SimpleBooleanProperty(this, BOARD__COLUMN_NAME__FAVORITE, _favorite);
        }
        
        return favoriteProperty;
    }
    // END  FAVORITE -----------------------------------------------------------
    
    // START  EMPLOYEER-IDS ----------------------------------------------------
    private ListProperty<Long>  employeerIdsProperty = null;
    private final ArrayList<Long> _employeerIds      = new ArrayList();
    
    @Column(name = BOARD__COLUMN_NAME__EMPLOYEER_IDS)
    public ArrayList<Long> getEmployeerIds() {
        if (employeerIdsProperty == null) {
            return _employeerIds;
        } else {
            return new ArrayList<>(employeerIdsProperty.get());
        }
    }
    
    public void setEmployeerIds(final ArrayList<Long> employeerIds) {
        if (employeerIdsProperty == null) {
            _employeerIds.clear();
            _employeerIds.addAll(employeerIds);
        } else {
            employeerIdsProperty.setAll(employeerIds);
        }
    }
    
    public ListProperty<Long> employeerIdsProperty() {
        if (employeerIdsProperty == null) {
            employeerIdsProperty = new SimpleListProperty(this, BOARD__COLUMN_NAME__EMPLOYEER_IDS, FXCollections.observableArrayList(_employeerIds));
        }
        
        return employeerIdsProperty;
    }
    // END  EMPLOYEER-IDS ------------------------------------------------------
    
    // START  TAGS-IDS ---------------------------------------------------------
    private ListProperty<Long>  tagsIdsProperty = null;
    private final ArrayList<Long> _tagsIds      = new ArrayList();
    
    @Column(name = BOARD__COLUMN_NAME__TAG_IDS)
    public ArrayList<Long> getTagIds() {
        if (tagsIdsProperty == null) {
            return _tagsIds;
        } else {
            return new ArrayList<>(tagsIdsProperty.get());
        }
    }
    
    public void setTagIds(final ArrayList<Long> tagsIds) {
        if (tagsIdsProperty == null) {
            _tagsIds.clear();
            _tagsIds.addAll(tagsIds);
        } else {
            tagsIdsProperty.setAll(tagsIds);
        }
    }
    
    public ListProperty<Long> tagsIdsProperty() {
        if (tagsIdsProperty == null) {
            tagsIdsProperty = new SimpleListProperty(this, BOARD__COLUMN_NAME__TAG_IDS, FXCollections.observableArrayList(_tagsIds));
        }
        
        return tagsIdsProperty;
    }
    // END  TAGS-IDS -----------------------------------------------------------
    
    // START  LINKS-IDS --------------------------------------------------------
    private ListProperty<Long>  linkIdsProperty = null;
    private final ArrayList<Long> _linkIds      = new ArrayList();
    
    @Column(name = BOARD__COLUMN_NAME__LINK_IDS)
    public ArrayList<Long> getLinkIds() {
        if (linkIdsProperty == null) {
            return _linkIds;
        } else {
            return new ArrayList<>(linkIdsProperty.get());
        }
    }
    
    public void setLinkIds(final ArrayList<Long> linkIds) {
        if (linkIdsProperty == null) {
            _linkIds.clear();
            _linkIds.addAll(linkIds);
        } else {
            linkIdsProperty.setAll(linkIds);
        }
    }
    
    public ListProperty<Long> linksIdsProperty() {
        if (linkIdsProperty == null) {
            linkIdsProperty = new SimpleListProperty(this, BOARD__COLUMN_NAME__LINK_IDS, FXCollections.observableArrayList(_linkIds));
        }
        
        return linkIdsProperty;
    }
    // END  LINKS-IDS ----------------------------------------------------------

    @Override
    public int compareTo(final Board other) {
        return new CompareToBuilder()
                .append(this.getIndex(),          other.getIndex())
                .append(this.getId(),             other.getId())
                .append(this.getGenerationTime(), other.getGenerationTime())
                .append(this.getName(),           other.getName())
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
        
        final Board other = (Board) obj;
        return new EqualsBuilder()
                .append(this.getId(),             other.getId())
                .append(this.getGenerationTime(), other.getGenerationTime())
                .append(this.getName(),           other.getName())
                .append(this.getDescription(),    other.getDescription())
                .append(this.getIndex(),          other.getIndex())
                .append(this.getFavorite(),       other.getFavorite())
                .isEquals();
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(this.getId())
                .append(this.getGenerationTime())
                .append(this.getName())
                .append(this.getDescription())
                .append(this.getIndex())
                .append(this.getFavorite())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(BOARD__COLUMN_NAME__ID,              this.getId())
                .append(BOARD__COLUMN_NAME__GENERATION_TIME, this.getGenerationTime())
                .append(BOARD__COLUMN_NAME__NAME,            this.getName())
                .append(BOARD__COLUMN_NAME__DESCRIPTION,     this.getDescription())
                .append(BOARD__COLUMN_NAME__INDEX,           this.getIndex())
                .append(BOARD__COLUMN_NAME__FAVORITE,        this.getFavorite())
                .toString();
    }
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(   this.getId());
        out.writeLong(   this.getGenerationTime());
        out.writeObject( this.getName());
        out.writeObject( this.getDescription());
        out.writeInt(    this.getIndex());
        out.writeBoolean(this.getFavorite());
        out.writeObject( this.getEmployeerIds());
        out.writeObject( this.getTagIds());
        out.writeObject( this.getLinkIds());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setId(in.readLong());
        this.setGenerationTime(in.readLong());
        this.setName(String.valueOf(in.readObject()));
        this.setDescription(String.valueOf(in.readObject()));
        this.setIndex(in.readInt());
        this.setFavorite(in.readBoolean());
        this.setEmployeerIds((ArrayList<Long>)in.readObject());
        this.setTagIds((ArrayList<Long>)in.readObject());
        this.setLinkIds((ArrayList<Long>)in.readObject());
    }
     
}
